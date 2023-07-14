from rest_framework import status
from rest_framework.response import Response
from django.http import JsonResponse
from .pagination import PageNumberPagination
from rest_framework.views import APIView
from rest_framework.viewsets import ModelViewSet
from rest_framework.filters import SearchFilter
from django.shortcuts import get_object_or_404

from .serializers import *
from .models import *
from .producer import publish, search_publish
import json, jwt
from django.conf import settings

class MovieView(ModelViewSet):
    queryset = Movie.objects.all()
    serializer_class = MovieResponseSerializer

    pagination_class = PageNumberPagination
    
    def get_queryset(self):
        qs= Movie.objects.all().order_by('-created_at')
        
        search_field = self.request.query_params.get('title')
        
        if search_field is not None:
            qs = qs.filter(title__icontains=search_field)

        return qs
    
    
    def create(self, request):
        queryset = Movie.objects.all()
        serializer = MovieSerializer(data=request.data)
        print(request.data)

        if serializer.is_valid():
            serializer.save()
            last_qs = Movie.objects.last()

            create_movie_json_data = {
                "movie_idx" : last_qs.movie_idx,
                "thumbnail_url" : last_qs.thumbnail_url,
                "genre" : None
            }

            publish('create_movie', create_movie_json_data)
            search_publish('create_movie', create_movie_json_data)


        return JsonResponse({'message' : 'Success'})
    
    

class MovieDefailView(APIView):
    
    def get_object(self, pk):
        movie = get_object_or_404(Movie, pk = pk)
        return movie

    def get(self, request, pk):
        
        # if IsAuthenticated:
        #     return Response(status=status.HTTP_401_UNAUTHORIZED)
        
        movie = self.get_object(pk)
        serializer = MovieSerializer(movie)
        serialized_data = serializer.data
        actror_data = serializer.data.get('actor')
        director_data = serializer.data.get('director')

        for i, val in enumerate(actror_data):
            actor_data = {
                'idx' : val,
                'profile_url' :  Actor.objects.get(pk=val).profile_url,
                'name' : Actor.objects.get(pk=val).name
                }
            serialized_data['actor'][i] = actor_data

        for j, val in enumerate(director_data):
            director_data = {
                'idx': val,
                'profile_url': Director.objects.get(pk=val).profile_url,
                'name' : Director.objects.get(pk=val).name
            }
            serialized_data['director'][j] = director_data

        return Response(serialized_data, status=status.HTTP_200_OK)

    def delete(self, request, pk):
        # if IsAuthenticated:
        #     return Response(status=status.HTTP_401_UNAUTHORIZED)
        
        queryset = Movie.objects.get(pk=pk)
        queryset.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)

    def patch(self, request, pk):
        # if IsAuthenticated:
        #     return Response(status=status.HTTP_401_UNAUTHORIZED)
        
        queryset = Movie.objects.get(pk=pk)
        serializer = MovieSerializer(queryset, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(status=status.HTTP_205_RESET_CONTENT)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class MovieHistoryViewSet(ModelViewSet):
    queryset = MovieHistory.objects.all()
    serializer_class = MovieHistorySerializer

    def get_object(self, request):
        key = request.headers.get('Authorization')
        payload = jwt.decode(key, settings.JWT_SECRET_KEY, algorithms='HS256')
        account_idx = MovieHistory.objects.filter(account_idx=payload['sub'])
        return account_idx
    
    def create(self, request):
        queryset = Movie.objects.all()
        serializers = self.serializer_class(data=request.data)

        if serializers.is_valid():
            key = request.headers.get('Authorization')
            payload = jwt.decode(key, settings.JWT_SECRET_KEY, algorithms='HS256')
            account = payload['sub']

            account_data = Account.objects.get(account_idx=account)  

            movie_idx = request.data.get('movie_idx')
            movie_qs_filter = queryset.get(movie_idx=movie_idx)

            movie_title = movie_qs_filter.title
            movie_image = movie_qs_filter.thumbnail_url

            serializers.save(title=movie_title, thumbnail_url = movie_image, account_idx = account_data)
            # 여기서 account_idx = header jwt에 있는 account_idx 로 바꿔야함.
            publish_queryset = MovieHistory.objects.filter(account_idx=str(account)).values('account_idx', 'movie_idx')
            json_object = json.dumps(list(str(publish_queryset)), indent = 4)
            publish('create_record', json_object)
            
            return Response(data=serializers.data, status=status.HTTP_201_CREATED)
        else:
            return Response(data=serializers.errors, status=status.HTTP_400_BAD_REQUEST)
        
    # def list(self, request, account_index):
    #     movie_history = self.get_object(account_index)
    #     serializer = MovieHistorySerializer(movie_history, many=True)
    #     return Response(serializer.data, status=status.HTTP_200_OK)
    
    def update(self, request, pk=None) :
        movie_history = MovieHistory.objects.get(id=pk)
        serializer = MovieHistorySerializer(instance=movie_history,data=request.data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        publish('update_record', serializer.data)
        return Response(serializer.data, status=status.HTTP_202_ACCEPTED)

    def destroy(self,request, pk = None):
        movie_history = MovieHistory.objects.get(id=pk)
        movie_history.delete()
        publish('delete_record', pk)
        return Response(status=status.HTTP_204_NO_CONTENT)
    
class HistoryDetailView(APIView):
    def get(self, request, pk):
        key = request.headers.get('Authorization')
        payload = jwt.decode(key, settings.JWT_SECRET_KEY, algorithms='HS256')
        account_idx = MovieHistory.objects.filter(account_idx=payload['sub'], movie_idx=pk)
        
        data = {
            'account_idx' : payload['sub'],
            'history_time' : account_idx[0].history_time
        }
        return JsonResponse(data)

class ActorViewSet(ModelViewSet):
    queryset = Actor.objects.all()
    serializer_class = ActorSerializer

    def get_object(self, pk):
        actor = get_object_or_404(Actor, pk = pk)
        return actor
    
    def get_queryset(self):
        qs= Actor.objects.all()

        name = self.request.query_params.get('name')

        if name is not None:
            qs = qs.filter(name__icontains=name)
            
        return qs

class ActorDefailView(APIView):
    def get_object(self, pk):
        actor = get_object_or_404(Actor, pk = pk)
        return actor

    def get(self, request, pk):
        actor = self.get_object(pk)
        serializer = ActorSerializer(actor)
        serialized_data = serializer.data
        idx_data = serializer.data.get('idx')

        movie_objects = Movie.objects.all().filter(actor__contains = [idx_data])
        movie_qs_values = movie_objects.values('movie_idx', 'thumbnail_url')

        for i, val in enumerate(movie_qs_values):   
            serialized_data['movie_list'].append(val)
        return Response(serialized_data, status=status.HTTP_200_OK)
    
class DirectorViewSet(ModelViewSet):
    queryset = Director.objects.all()
    serializer_class = DirectorSerializer

    def get_queryset(self):
        qs= Director.objects.all()

        name = self.request.query_params.get('name')

        if name is not None:
            qs = qs.filter(name__icontains=name)
            
        return qs

class DirectorDefailView(APIView):
    def get_object(self, pk):
        director = get_object_or_404(Director, pk = pk)
        return director

    def get(self, request, pk):
            director = self.get_object(pk)
            serializer = DirectorSerializer(director)

            serialized_data = serializer.data
            idx_data = serializer.data.get('idx')

            movie_objects = Movie.objects.all().filter(actor__contains = [idx_data])
            movie_qs_values = movie_objects.values('movie_idx', 'thumbnail_url')

            for i, val in enumerate(movie_qs_values):   
                serialized_data['movie_list'].append(val)

            return Response(serializer.data, status=status.HTTP_200_OK)

class PornoDeleteView(APIView):
    def post(self, request, pk):
        porno = Movie.objects.get(id=pk)
        porno.delete()

        return Response(status=status.HTTP_204_NO_CONTENT)
