import pika
import json
from models import View_Record, Genre_Data


params = pika.URLParameters('amqps://igqylvwy:TcwMgVG-nqWB4Riz7lSMPp17hEg3qOAC@vulture.rmq.cloudamqp.com/igqylvwy')

connection = pika.BlockingConnection(params)

channel = connection.channel()

channel.queue_declare(queue='AI')


def callback(ch, method, properties, body):
    print('Received in boss')
    data = json.loads(body)
    print(data)

    if properties.content_type == 'Create_Record':
        record_arr = data["record"]
        dong = list(map(lambda x: x["id"], record_arr))
        view = View_Record(account_id=data['account_id'],record=json.dumps(dong))
        view.save()

    elif properties.content_type == 'Update_Record':
        record_arr = data["record"]
        dong = list(map(lambda x: x["id"], record_arr))
        view = View_Record.objects.get(account_id=data['account_id'])
        view.record = json.dumps(dong)
        view.save()

    elif properties.content_type == 'Delete_Record':
        view = View_Record.objects.get(account_id=data['account_id'])
        view.delete()

    elif properties.content_type == 'Create_Movie':
        genre_arr = data["genre"]
        dong = list(map(lambda x: x["id"], genre_arr))
        movie = Genre_Data(movie_id=data['movie_id'], genre=json.dumps(dong))
        movie.save()

    elif properties.content_type == 'Update_Movie':
        genre_arr = data["genre"]
        dong = list(map(lambda x: x["id"], genre_arr))
        movie = Genre_Data.objects.get(movie_id=data['movie_id'])
        movie.genre = json.dumps(dong)
        movie.save()

    elif properties.content_type == 'Delete_Movie':
        movie = Genre_Data.objects.get(movie_id=data['movie_id'])
        movie.delete()


channel.basic_consume(queue='AI', on_message_callback=callback, auto_ack=True)


print('Started consuming')

channel.start_consuming()

channel.close()

