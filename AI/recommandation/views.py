import os

from django.db.models import Count
from django.shortcuts import render
from .models import ViewRecord, GenreData, DefaultRecommandation, Recommandation
from rest_framework import viewsets
from .serializers import ViewSerializer, DefaultSerializer
from django.core import serializers
from rest_framework.response import Response
from rest_framework.decorators import api_view
import json, random, jwt, environ
from datetime import datetime
today = datetime.now()
env = environ.Env(
    # set casting, default value
    DEBUG=(bool, False)
)
genres = [
    '액션', '로맨스', '코미디', '스릴러', '드라마',
    'SF', '애니메이션', '공포', '모험', '판타지'
]


def popular():
    result = {}
    view = ViewRecord.objects.all()
    genre = GenreData.objects.all()
    d = {}
    for i in range(100, genre.count() + 100):
        d[i] = view.filter(record__contains=[i]).count()
        result = dict(sorted(d.items(), key=lambda x: x[1], reverse=True)[:10])
    return result


@api_view(['GET'])
def get_popular(req):
    genre = GenreData.objects.all()
    # default = DefaultRecommandation.objects.get()
    recommend_list = []
    default_list = []
    result = popular()
    print(list(result.keys()))
    for i in list(result.keys()):
        recommend_list.append(genre.filter(movie_idx=i).values('movie_idx', 'imageUrl'))
    return Response(recommend_list)



@api_view(['GET'])
def get_personal_recommend(req):
    key = req.headers.get('Authorization')
    payload = jwt.decode(key, os.environ.get('JWT_SECRET_KEY'), algorithms='HS256')
    view = ViewRecord.objects.all()
    genre = GenreData.objects.all()
    history = view.filter(account_idx=payload['sub']).values("record")[0]['record']
    result = [genre.filter(movie_idx=i).values('genre')[0]['genre'] for i in history]
    dong = {}
    results = {}
    recommend_list = []
    for i in genres:
        dong[i] = result.count(i)
        results = dict(sorted(dong.items(), key=lambda x: x[1], reverse=True)[:1])
    populars = popular()
    keys = list(populars.keys())
    f_genre = genre.filter(genre__contains=list(results.keys()))
    movie_list = [f_genre.values('movie_idx')[i]['movie_idx'] for i in range(f_genre.count())]
    for i in random.sample(movie_list, 5):
        print(i)
        if int(i) in keys:
            continue
        keys.append(int(i))
    random.shuffle(keys)
    for i in keys[:10]:
        recommend_list.append(genre.filter(movie_idx=i).values('movie_idx', 'imageUrl'))
    return Response(recommend_list)
# Create your views here.
