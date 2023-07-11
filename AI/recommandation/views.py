from django.db.models import Count
from django.shortcuts import render
from .models import ViewRecord, GenreData, DefaultRecommandation, Recommandation
from rest_framework import viewsets
from .serializers import ViewSerializer
from rest_framework.response import Response
from rest_framework.decorators import api_view
import json


@api_view(['GET'])
def get_popular(req, pk):
    global hihi
    view = ViewRecord.objects.all()
    genre = GenreData.objects.all()
    d = {}
    result = {}
    recommend_list = []
    for i in range(100,genre.count()+100):
        d[i] = view.filter(record__contains=[i]).count()
        result = dict(sorted(d.items(), key=lambda x: x[1], reverse=True)[:5])
    for i in result.keys():
        recommend_list.append( genre.filter(movie_idx=i).values('movie_idx', 'imageUrl' ))
    return Response(recommend_list)

# Create your views here.
