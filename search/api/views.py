from rest_framework import viewsets
from .serializers import SearchSeriaizer
from .models import Search
import itertools 

class SearchViewSet(viewsets.ModelViewSet):
    queryset = Search.objects.all()
    serializer_class = SearchSeriaizer        

    def get_queryset(self):
        qs= Search.objects.all()
        qs2 = Search.objects.all()

        search_field = self.request.query_params.get('search_field')

        if search_field is not None:
            qs = qs.filter(title__icontains=search_field)
            qs2 = qs2.filter(genre__icontains=search_field)
            
        return list(itertools.chain(qs, qs2))