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

channel.queue_declare(queue='AI')


def callback(ch, method, properties, body):
    print('Received in boss')
    data = json.loads(body)
    print(data)
    if properties.content_type == 'create_record':
        record_arr = data["record"]
        dong = list(map(lambda x: x["id"], record_arr))
        view = View_Record(account_id=data['account_id'],record=json.dumps(dong))
        view.save()

    elif properties.content_type == 'update_record':
        record_arr = data["record"]
        dong = list(map(lambda x: x["id"], record_arr))
        view = View_Record.objects.get(account_id=data['account_id'])
        view.record = json.dumps(dong)
        view.save()

    elif properties.content_type == 'delete_record':
        view = View_Record.objects.get(account_id=data['account_id'])
        view.delete()

    elif properties.content_type == 'create_movie':
        genre_arr = data["genre"]
        dong = list(map(lambda x: x["id"], genre_arr))
        movie = Genre_Data(movie_id=data['movie_id'], genre=json.dumps(dong))
        movie.save()

    elif properties.content_type == 'update_movie':
        genre_arr = data["genre"]
        dong = list(map(lambda x: x["id"], genre_arr))
        movie = Genre_Data.objects.get(movie_id=data['movie_id'])
        movie.genre = json.dumps(dong)
        movie.save()

    elif properties.content_type == 'delete_movie':
        movie = Genre_Data.objects.get(movie_id=data['movie_id'])
        movie.delete()


channel.basic_consume(queue='AI', on_message_callback=callback, auto_ack=True)

print('Started consuming')

channel.start_consuming()

channel.close()
