from rest_framework.filters import SearchFilter
from rest_framework import viewsets

from .serializers import TestSeriaizer
from .models import Test
from .pagination import PageNumberPagination

class SearchViewSet(viewsets.ModelViewSet):
    queryset = Test.objects.all()
    serializer_class = TestSeriaizer

    filter_backends = [SearchFilter]
    search_fields = ('title', 'description',)