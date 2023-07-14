from django.db import models

class Search(models.Model):
    title = models.CharField(max_length=255)
    genre = models.JSONField()
    view_count = models.IntegerField(default=0)
    
class Genre(models.Model):
    keyword = models.CharField(max_length=255)
    view_count = models.IntegerField(default=0)

class Movie(models.Model):
    # request
    movie_idx = models.AutoField(primary_key=True, null = False)
    thumbnail_url = models.URLField(default="",max_length=2000)

    # response
    title = models.CharField(max_length=255)
    genre = models.JSONField()

class Crowd(models.Model):
    crowd_idx = models.AutoField(primary_key=True, null=False)
    title = models.CharField(max_length=255)