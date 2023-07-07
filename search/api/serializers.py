from rest_framework import serializers
from .models import *

class SearchSeriaizer(serializers.ModelSerializer):
    class Meta:
        model = Search
        fields = ('id', 'title', 'genre')

class SearchTagSerializer(serializers.ModelSerializer):
    class Meta:
        model = Search
        fields = ('genre',)