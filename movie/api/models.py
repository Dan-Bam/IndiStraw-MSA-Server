from django.db import models

class Auth(models.Model):
    pass

class Movie(models.Model):
    # account_index = models.IntegerField()
    title = models.CharField(max_length=255)
    description = models.TextField()
    movie_url = models.URLField(default = "")
    thumbnail_url = models.URLField(default = "")
    director = models.JSONField(default='{}')
    actor = models.JSONField(default='{}')
    genre = models.JSONField(default='{}')
    movie_highlight = models.JSONField(default='{}')

class MovieHistory(models.Model):
    movie_idx = models.ForeignKey("Movie", on_delete=models.CASCADE)
    title = models.CharField(max_length=255)
    account_index = models.IntegerField()
    image_url = models.URLField(default = "")
    history_time = models.DecimalField(max_digits = 5, decimal_places = 3)