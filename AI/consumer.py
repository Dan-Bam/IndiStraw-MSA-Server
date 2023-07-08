import os

import pika
import json
os.environ.setdefault("DJANGO_SETTINGS_MODULE", 'AI.settings')

import django

django.setup()

from recommandation.models import ViewRecord, GenreData
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
            db_data = ViewRecord.objects.get(account_id=data[0]['account_index'])
        except:
            view = ViewRecord(account_id=data[0]['account_index'], record=json.dumps(history))
            view.save()
        else:
            db_data.record = json.dumps(history)
            db_data.save()


    elif properties.content_type == 'delete_record':
        view = ViewRecord.objects.get(account_id=data[0]['account_index'])
        view.delete()

    elif properties.content_type == 'create_movie':
        genre = list(map(lambda x: x["movie_idx"], data))
        movie = GenreData(movie_id=data[0]['movie_idx'], genre=json.dumps(genre))
        movie.save()

    elif properties.content_type == 'update_movie':
        genre = list(map(lambda x: x["movie_idx"], data))
        movie = GenreData.objects.get(movie_id=data[0]['movie_idx'])
        movie.genre = json.dumps(genre)
        movie.save()

    elif properties.content_type == 'delete_movie':
        movie = GenreData.objects.get(movie_id=data[0]['movie_idx'])
        movie.delete()


channel.basic_consume(queue='ai', on_message_callback=callback, auto_ack=True)

print('Started consuming')

channel.start_consuming()

channel.close()
