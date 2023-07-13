from rest_framework import serializers
from .models import *


class SearchSeriaizer(serializers.ModelSerializer):
    class Meta:
        model = Search
        fields = ('title',)

class SearchTagSerializer(serializers.ModelSerializer):
    class Meta:
        model = Genre
        fields = ('keyword', 'view_count')

class MovieSearchSerializer(serializers.ModelSerializer):
    class Meta:
        model = Movie
        fields = ('movie_idx', 'thumbnail_url')