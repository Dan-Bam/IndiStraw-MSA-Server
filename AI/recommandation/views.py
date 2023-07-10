from django.db.models import Count
from django.shortcuts import render
from .models import ViewRecord, GenreData, DefaultRecommandation, Recommandation
from rest_framework import viewsets
from .serializers import ViewSerializer
from rest_framework.response import Response
from rest_framework.decorators import api_view
import json


@api_view(['GET'])
def get_queryset(req):
    view = ViewRecord.objects.all()
    genre = GenreData.objects.all()
    d = {}
    result = []

    #dong = json.loads(view.only('record'))
    #for i in range(genre.count()):
        #d[i] = view.filter(record__contains=i).count()
        #hihi = dict(sorted(d.items(), key=lambda x: x[1], reverse=True))
    # for i in range(3):
    #     result.append(max(d,key=d.get))
    return Response(view.only('record'))

# Create your views here.
