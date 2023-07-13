from rest_framework import serializers
from .models import *

class MovieResponseSerializer(serializers.ModelSerializer):
    class Meta:
        model = Movie
        fields = ('movie_idx', 'thumbnail_url', )

class MovieSerializer(serializers.ModelSerializer):
    class Meta:
        model = Movie
        fields = ('title','description', 'movie_url', 'thumbnail_url', 'director', 'actor', 'movie_highlight', 'clowd_true', 'genre', )

class MovieResponseSerializera(serializers.ModelSerializer):
    class Meta:
        model = Movie
        fields = ('title', 'description','movie_url', 'thumbnail_url', 'movie_highlight', 'clowd_true' )

class TestMovieSerializer(serializers.ModelSerializer):
    class Meta:
        model = Movie
        fields = '__all__'
class MovieHistorySerializer(serializers.ModelSerializer):
    class Meta:
        model = MovieHistory
        fields = '__all__'


class ActorResponseSerializer(serializers.ModelSerializer):
    class Meta:
        model = Actor
        fields = ('idx', 'profile_url', 'name', )

class ActorSerializer(serializers.ModelSerializer):
    class Meta:
        model = Actor
        fields = '__all__'

class DirectorSerializer(serializers.ModelSerializer):
    class Meta:
        model = Director
        fields = ('idx', 'profile_url', 'name', )