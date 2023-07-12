import pika
import os, json

os.environ.setdefault("DJANGO_SETTINGS_MODULE",'search.settings')

import django 

django.setup()

from api.models import * 

params = pika.URLParameters('amqp://danbam0907:danbam1234!@svc.sel4.cloudtype.app:31704/')

connection = pika.BlockingConnection(params)

channel = connection.channel()

channel.queue_declare(queue='search')


def callback(ch, method, properties, body):
    print('Received')
    data = json.loads(body)
    print(data)


    if properties.content_type == 'create_crowdfunding':
        # clowd = Clowd(clowd_idx = data['idx'], title = data['title'], description = data['description'], percentage = data['percentage'],
        #         thumbnailUrl = data['thumbnailUrl'], status = data['status'])
        # clowd.save()
        print('suc')

print('Account idx has been saved.')

channel.exchange_declare(exchange='direct')
channel.queue_bind(exchange='direct', queue='search', routing_key='create_crowdfunding')
channel.basic_consume(queue='search', on_message_callback=callback, auto_ack=True)

print('Started consuming')

channel.start_consuming()

channel.close()





