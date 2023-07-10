from rest_framework import status
from rest_framework.permissions import IsAuthenticated
from rest_framework.filters import SearchFilter
from rest_framework.response import Response
from django.http import JsonResponse
from rest_framework.pagination import PageNumberPagination
from rest_framework.views import APIView
from rest_framework.viewsets import ModelViewSet

from django.shortcuts import render, get_object_or_404

from .serializers import *
from .models import *
from .producer import publish
import json

class MovieView(APIView):


    def post(self, request):
        queryset = Movie.objects.all()
        serializer = MovieSerializer(data=request.data)

        if serializer.is_valid():
            serializer.save()
            return JsonResponse({'message' : 'Success'})
    
    def get(self, request):
        queryset = Movie.objects.all()
        serializer = MovieResponseSerializer(queryset, many=True)

        return Response(serializer.data, status=status.HTTP_200_OK)
    

class MovieDefailView(APIView):
    
    def get_object(self, pk):
        movie = get_object_or_404(Movie, pk = pk)
        return movie

    def get(self, request, pk):
        # if IsAuthenticated:
        #     return Response(status=status.HTTP_401_UNAUTHORIZED)
        
        movie = self.get_object(pk)
        serializer = MovieSerializer(movie)
        return Response(serializer.data, status=status.HTTP_200_OK)

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
        serializer = MovieSerializer(queryset, data=request, partial=True)

        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_205_RESET_CONTENT)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class MovieHistoryViewSet(ModelViewSet):
    queryset = MovieHistory.objects.all()
    serializer_class = MovieHistorySerializer
    pagination_class = PageNumberPagination

    def get_object(self, account_idx):
        account_idx = MovieHistory.objects.filter(account_idx=account_idx)
        return account_idx
    
    def create(self, request):
        queryset = Movie.objects.all()
        serializers = self.serializer_class(data=request.data)

        if serializers.is_valid():
            movie_idx = request.data.get('movie_idx')
            movie_qs_filter = queryset.get(movie_idx=movie_idx)
            movie_title = movie_qs_filter.title
            movie_image = movie_qs_filter.thumbnail_url
            serializers.save(title=movie_title, thumbnail_url = movie_image) 
            
            publish_queryset = MovieHistory.objects.filter(account_idx=1).values('account_idx', 'movie_idx')
            json_object = json.dumps(list(publish_queryset), indent = 4) 
            print(json_object)
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
    

class ActorViewSet(ModelViewSet):
    queryset = Actor.objects.all()
    serializer_class = ActorSerializer
    pagination_class = PageNumberPagination

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
        return Response(serializer.data, status=status.HTTP_200_OK)
    
class DirectorViewSet(ModelViewSet):
    queryset = Director.objects.all()
    serializer_class = DirectorSerializer
    pagination_class = PageNumberPagination

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
        return Response(serializer.data, status=status.HTTP_200_OK)