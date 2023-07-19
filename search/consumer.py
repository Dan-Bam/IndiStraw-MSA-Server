import os

import pika
import json
os.environ.setdefault("DJANGO_SETTINGS_MODULE", 'search.settings')

import django

django.setup()

from api.models import *

params = pika.URLParameters('amqps://igqylvwy:TcwMgVG-nqWB4Riz7lSMPp17hEg3qOAC@vulture.rmq.cloudamqp.com/igqylvwy')

connection = pika.BlockingConnection(params)

channel = connection.channel()

channel.queue_declare(queue='search')


def callback(ch, method, properties, body):
    print('Received')
    data = json.loads(body)
    print(data)

    if properties.content_type == 'create_movie':
        movie = Movie(movie_idx = data['movie_idx'], thumbnail_url=data['thumbnail_url'])
        movie.save()
        print('suc')


channel.basic_consume(queue='search', on_message_callback=callback, auto_ack=True)

print('Started consuming')

channel.start_consuming()

channel.close()