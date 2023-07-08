from django.db.models import Count
from django.shortcuts import render
from .models import View_Record, Genre_Data, Default_Recommandation, Recommandation
from rest_framework import viewsets
from .serializers import ViewSerializer
from rest_framework.response import Response
class ViewSet(viewsets.ModelViewSet):
    queryset = View_Record.objects.all()
    serializer_class = ViewSerializer
    def get_queryset(self):
        view = View_Record.objects.all()
        genre = Genre_Data.objects.all()
        d = {}
        result = []
        for i in range(genre.count()):
            d[i] = view.filter(record__contains=i).count()
        #hihi = dict(sorted(d.items(), key=lambda x: x[1], reverse=True))
        for i in range(3):
            result.append(max(d,key=d.get))


        return list(view)

# Create your views here.
