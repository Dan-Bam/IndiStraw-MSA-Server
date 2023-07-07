from rest_framework import viewsets
from .serializers import SearchSeriaizer
from .models import Search, Genre
import itertools 
from .pagination import PageNumberPagination

class SearchViewSet(viewsets.ModelViewSet):
    queryset = Search.objects.all()
    serializer_class = SearchSeriaizer        
    pagination_class = PageNumberPagination

    def get_queryset(self):
        qs= Search.objects.all()
        qs2 = Search.objects.all()
        
        search_field = self.request.query_params.get('keyword')
        
        
        if search_field is not None:
            qs = qs.filter(title__icontains=search_field)
            qs2 = qs2.filter(genre__keyword__contains= [search_field])

            qs2.update(view_count=+1)

        return list(itertools.chain(qs, qs2))