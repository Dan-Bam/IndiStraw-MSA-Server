from rest_framework import viewsets
from .serializers import SearchSeriaizer, SearchTagSerializer
from .models import Search, Genre
import itertools 
import json
from .pagination import PageNumberPagination

class SearchViewSet(viewsets.ModelViewSet):
    queryset = Search.objects.all()
    serializer_class = SearchSeriaizer

    def get_queryset(self):
        qs= Search.objects.all()
        qs2 = Search.objects.all()
        
        search_field = self.request.query_params.get('keyword')
        
        
        if search_field is not None:
            qs = qs.filter(title__icontains=search_field)
            qs2 = qs2.filter(genre__keyword__contains= [search_field])

            if len(Genre.objects.all().filter(keyword__icontains=search_field)) > 0:
                count_object = Genre.objects.get(keyword=search_field)
                count_object.view_count += 1
                count_object.save()
            else:
                create_genre_object = Genre(keyword=search_field, view_count=1)
                create_genre_object.save()

        return list(itertools.chain(qs, qs2))
    
class SearchTagViewSet(viewsets.ModelViewSet):
    queryset = Genre.objects.all()
    serializer_class = SearchTagSerializer
    pagination_class = PageNumberPagination

    def get_queryset(self):
        qs = Genre.objects.all().order_by('-view_count')

        return qs