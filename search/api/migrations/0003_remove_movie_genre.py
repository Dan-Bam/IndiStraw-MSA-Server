# Generated by Django 4.2.1 on 2023-07-14 02:57

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('api', '0002_alter_movie_thumbnail_url'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='movie',
            name='genre',
        ),
    ]
