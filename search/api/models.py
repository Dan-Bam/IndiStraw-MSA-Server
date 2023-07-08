from django.db import models

class Search(models.Model):
    title = models.CharField(max_length=255)
    genre = models.JSONField()
    view_count = models.IntegerField(default=0)
    
class Genre(models.Model):
    keyword = models.CharField(max_length=255)
    view_count = models.IntegerField(default=0)