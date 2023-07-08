from django.db import models


class GenreData(models.Model):
    genre = models.CharField(max_length=100)  # 영화 DB에서 가져오는 ID에 해당하는 장르
    movie_id = models.CharField(max_length=9999)  # 영화 DB에서 가져오는 ID


class ViewRecord(models.Model):
    record = models.CharField(max_length=100)  # 계정 DB에서 가져오는 시청 기록값
    account_id = models.CharField(max_length=9999)


class DefaultRecommandation(models.Model):
    record = models.CharField(max_length=100)


class Recommandation(models.Model):
    account_id = models.ForeignKey(ViewRecord, on_delete=models.CASCADE)
    record = models.CharField(max_length=100)
# Create your models here.
