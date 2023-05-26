from rest_framework import status
from rest_framework.permissions import IsAuthenticated
from rest_framework.response import Response
from rest_framework.pagination import PageNumberPagination
from rest_framework.views import APIView
from .serializers import *
from .models import *
from .pagination import PaginationHandlerMixin
from django.shortcuts import render, get_object_or_404

class MoviePagination(PageNumberPagination):
    page_size = 20

class MovieAPIView(APIView, PaginationHandlerMixin):
    pagination_class = MoviePagination
    serializer_class = MovieSerializer

    def post(self, request):
        # if IsAuthenticated:
        #     return Response(status=status.HTTP_401_UNAUTHORIZED)
        
        serializer = MovieSerializer(data=request.data)
        
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
    
    def get(self, request, format=None, *args, **kwargs):
        # if IsAuthenticated:
        #     return Response(status=status.HTTP_401_UNAUTHORIZED)
        
        queryset = Movie.objects.all()
        page = self.paginate_queryset(queryset)
        if page is not None:
            serializer = self.get_paginated_response(self.serializer_class(page,many=True).data)
        else:
            serializer = self.serializer_class(queryset, many=True)
        
        return Response(serializer.data, status=status.HTTP_200_OK)
    
class MovieDefailView(APIView):
    
    def get_object(self, movie_id):
        movie = get_object_or_404(Movie, id = movie_id)
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
