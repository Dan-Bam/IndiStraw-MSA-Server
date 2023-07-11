# Create your models here.
from django.db import models
from django.contrib.postgres.fields import ArrayField
class GenreData(models.Model):
    genre = ArrayField(models.CharField(max_length=20), blank=True)  # 영화 DB에서 가져오는 ID에 해당하는 장르
    movie_idx = models.CharField(max_length=9999, primary_key=True)  # 영화 DB에서 가져오는 ID
    imageUrl = models.URLField(max_length=9999, default="https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png")


class ViewRecord(models.Model):
    record = ArrayField(models.IntegerField(default=0))  # 계정 DB에서 가져오는 시청 기록값
    account_idx = models.CharField(max_length=9999, primary_key=True)

class DefaultRecommandation(models.Model):
    record = models.CharField(max_length=100)
class Recommandation(models.Model):
    account_idx = models.ForeignKey(ViewRecord, on_delete=models.CASCADE, primary_key=True)
    record = ArrayField(models.IntegerField(default=0))
# Create your models here.