# Generated by Django 4.2.3 on 2023-07-13 10:47

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('recommandation', '0005_rename_image_url_genredata_imageurl_and_more'),
    ]

    operations = [
        migrations.DeleteModel(
            name='DefaultRecommandation',
        ),
        migrations.RenameField(
            model_name='genredata',
            old_name='imageUrl',
            new_name='thumbnail_url',
        ),
    ]
