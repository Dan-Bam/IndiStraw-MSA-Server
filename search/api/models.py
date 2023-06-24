from django.db import models

class Search(models.Model):
    title = models.CharField(max_length=255)
    genre = models.CharField(max_length=255)
