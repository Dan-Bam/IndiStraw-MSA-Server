from django.db import models
from django.contrib.postgres.fields import ArrayField

class Account(models.Model):
    account_idx = models.UUIDField()
    
class Movie(models.Model):
    # Request
    title = models.CharField(max_length=255)
    description = models.TextField()
    movie_url = models.URLField(default = "")
    thumbnail_url = models.URLField(default = "")
    director = ArrayField(models.CharField(max_length=255), blank=True)
    actor = ArrayField(models.CharField(max_length=255), blank=True)
    movie_highlight = ArrayField(models.CharField(max_length=255), blank=True)
    clowd_true = models.BooleanField(default=False)

    # Other
    movie_idx = models.AutoField(primary_key=True, null=False)
    genre = models.JSONField(default='{}', null=True)

class Actor(models.Model):
    profile_url = models.URLField()
    name = models.CharField(max_length=24)

class Director(models.Model):
    profile_url = models.URLField()
    name = models.CharField(max_length=24)

class MovieHistory(models.Model):
    movie_idx = models.ForeignKey("Movie", on_delete=models.CASCADE)
    account_idx = models.IntegerField()
    title = models.CharField(max_length=255, blank=True)
    thumbnail_url = models.URLField(default = "", blank=True)
    history_time = models.DecimalField(max_digits = 5, decimal_places = 3, null=True)
