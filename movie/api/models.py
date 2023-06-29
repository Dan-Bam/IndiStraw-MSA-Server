from django.db import models

class Movie(models.Model):
    account_index = models.ForeignKey("Account", on_delete=models.PROTECT)
    title = models.CharField(max_length=255)
    description = models.TextField()
    movie_url = models.URLField(default = "")
    thumbnail_url = models.URLField(default = "")
    director = models.JSONField(default='{}')
    actor = models.JSONField(default='{}')
    genre = models.JSONField(default='{}')
    movie_highlight = models.JSONField(default='{}')

class MovieHistory(models.Model):
    account_index = models.ForeignKey("Account", on_delete=models.CASCADE)
    movie_idx = models.ForeignKey("Movie", on_delete=models.CASCADE)
    title = models.CharField(max_length=255)
    image_url = models.URLField(default = "")
    history_time = models.DecimalField(max_digits = 5, decimal_places = 3)

class Account(models.Model):
    account_idx = models.IntegerField()