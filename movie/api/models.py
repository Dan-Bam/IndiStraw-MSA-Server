from django.db import models

class Movie(models.Model):
    account_index = models.ForeignKey("Account", on_delete=models.PROTECT)
    title = models.CharField(max_length=255)
    description = models.TextField()
    movie_url = models.URLField(default = "")
    thumbnail_url = models.URLField(default = "")
    director = models.ForeignKey("Director", on_delete=models.PROTECT)
    actor = models.ForeignKey("Actor", on_delete=models.PROTECT)
    genre = models.JSONField(default='{}')
    movie_highlight = models.JSONField(default='{}')

class Actor(models.Model):
    profile_url = models.URLField()
    name = models.CharField(max_length=24)

class Director(models.Model):
    profile_url = models.URLField()
    name = models.CharField(max_length=24)

class MovieHistory(models.Model):
    account_index = models.ForeignKey("Account", on_delete=models.CASCADE)
    movie_idx = models.ForeignKey("Movie", on_delete=models.CASCADE)
    title = models.CharField(max_length=255, blank=True)
    thumbnail_url = models.URLField(default = "", blank=True)
    history_time = models.DecimalField(max_digits = 5, decimal_places = 3, null=True)

class Account(models.Model):
    account_idx = models.IntegerField()