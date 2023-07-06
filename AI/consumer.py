import os

import pika
import json
os.environ.setdefault("DJANGO_SETTINGS_MODULE", 'AI.settings')

import django

django.setup()

from recommandation.models import View_Record, Genre_Data

params = pika.URLParameters('amqps://igqylvwy:TcwMgVG-nqWB4Riz7lSMPp17hEg3qOAC@vulture.rmq.cloudamqp.com/igqylvwy')

connection = pika.BlockingConnection(params)

channel = connection.channel()

channel.queue_declare(queue='ai')


def callback(ch, method, properties, body):
    print('Received')
    data = json.loads(body)
    print(data)
    if properties.content_type == 'create_record':
        raw_json = data["list"]
        dong = list(map(lambda x: x["id"], raw_json))
        view = View_Record(account_id=data['account_index'],record=json.dumps(dong))
        view.save()

    elif properties.content_type == 'update_record':
        raw_json = data["list"]
        dong = list(map(lambda x: x["id"], raw_json))
        view = View_Record.objects.get(account_id=data['account_index'])
        view.record = json.dumps(dong)
        view.save()

    elif properties.content_type == 'delete_record':
        view = View_Record.objects.get(account_id=data['account_index'])
        view.delete()

    elif properties.content_type == 'create_movie':
        genre_arr = data["list"]
        dong = list(map(lambda x: x["id"], genre_arr))
        movie = Genre_Data(movie_id=data['movie_idx'], genre=json.dumps(dong))
        movie.save()

    elif properties.content_type == 'update_movie':
        genre_arr = data["list"]
        dong = list(map(lambda x: x["id"], genre_arr))
        movie = Genre_Data.objects.get(movie_id=data['movie_idx'])
        movie.genre = json.dumps(dong)
        movie.save()

    elif properties.content_type == 'delete_movie':
        movie = Genre_Data.objects.get(movie_id=data['movie_idx'])
        movie.delete()


channel.basic_consume(queue='ai', on_message_callback=callback, auto_ack=True)

print('Started consuming')

channel.start_consuming()

channel.close()
