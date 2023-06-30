from rest_framework import status
from rest_framework.permissions import IsAuthenticated
from rest_framework.filters import SearchFilter
from rest_framework.response import Response
from rest_framework.pagination import PageNumberPagination
from rest_framework.views import APIView
from .serializers import *
from .models import *
from .pagination import PageNumberPagination
from django.shortcuts import render, get_object_or_404
from rest_framework.viewsets import ModelViewSet
from .producer import publish

class AccountViewSet(ModelViewSet):
    queryset = Account.objects.all()
    serializer_class = AccountSerializer
    pagination_class = PageNumberPagination


class MovieViewSet(ModelViewSet):
    queryset = Movie.objects.all()
    serializer_class = MovieSerializer
    pagination_class = PageNumberPagination

    filter_backends = [SearchFilter]
    search_fields = ('title', 'description',)


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

    def get_object(self, account_index):
        account_index = MovieHistory.objects.filter(account_index=account_index)
        return account_index
    
    def create(self, request):
        queryset = Movie.objects.all()
        serializers = self.serializer_class(data=request.data)

        if serializers.is_valid():
            movie_idx = request.data.get('movie_idx')
            movie_title = queryset.filter(id=movie_idx).title
            movie_image = queryset.filter(id=movie_idx).thumbnail_url
            serializers.save(title=movie_title, thumbnail_url = movie_image)
            publish('create_record', serializers.data)
            return Response(data=serializers.data, status=status.HTTP_201_CREATED)
        else:
            return Response(data=serializers.errors, status=status.HTTP_400_BAD_REQUEST)
        
    def list(self, request, account_index):
        movie_history = self.get_object(account_index)
        serializer = MovieHistorySerializer(movie_history, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)