import os

import pika
import json
os.environ.setdefault("DJANGO_SETTINGS_MODULE", 'AI.settings')

import django

django.setup()
from recommandation import models
#from models import models.ViewRecord, models.GenreData
params = pika.URLParameters('amqps://igqylvwy:TcwMgVG-nqWB4Riz7lSMPp17hEg3qOAC@vulture.rmq.cloudamqp.com/igqylvwy')

connection = pika.BlockingConnection(params)

channel = connection.channel()

channel.queue_declare(queue='ai')


def callback(ch, method, properties, body):
    print('Received')
    temp = json.loads(body)
    data = json.loads(temp)
    print(data)
    if properties.content_type == 'create_record':
        history = list(map(lambda x: x["movie_idx"], data))
        try:
            db_data = models.ViewRecord.query.get(account_id=data[0]['account_idx'])
        except:
            view = models.ViewRecord(account_id=data[0]['account_idx'], record=history)
            view.save()
        else:
            db_data.record = history
            db_data.save()


    elif properties.content_type == 'delete_record':
        view = models.ViewRecord.query.get(account_id=data[0]['account_idx'])
        view.delete()

    elif properties.content_type == 'create_movie':
        genre = list(map(lambda x: x["movie_idx"], data))
        movie = models.GenreData(movie_id=data[0]['movie_idx'], genre=genre)
        movie.save()

    elif properties.content_type == 'update_movie':
        genre = list(map(lambda x: x["movie_idx"], data))
        movie = models.GenreData.query.get(movie_id=data[0]['movie_idx'])
        movie.genre = genre
        movie.save()

    elif properties.content_type == 'delete_movie':
        movie = models.GenreData.query.get(movie_id=data[0]['movie_idx'])
        movie.delete()


channel.basic_consume(queue='ai', on_message_callback=callback, auto_ack=True)

print('Started consuming')

channel.start_consuming()

channel.close()
