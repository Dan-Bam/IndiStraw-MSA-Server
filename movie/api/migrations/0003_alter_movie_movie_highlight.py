# Generated by Django 4.2.1 on 2023-07-14 00:40

import django.contrib.postgres.fields
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('api', '0002_alter_account_account_idx'),
    ]

    operations = [
        migrations.AlterField(
            model_name='movie',
            name='movie_highlight',
            field=django.contrib.postgres.fields.ArrayField(base_field=models.URLField(max_length=2000), blank=True, size=None),
        ),
    ]
