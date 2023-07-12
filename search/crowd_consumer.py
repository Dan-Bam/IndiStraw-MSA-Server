import pika
import os, json

os.environ.setdefault("DJANGO_SETTINGS_MODULE",'search.settings')

import django 

django.setup()

from api.models import Crowd

params = pika.URLParameters('amqp://danbam0907:danbam1234!@svc.sel4.cloudtype.app:31704/')

connection = pika.BlockingConnection(params)

channel = connection.channel()

channel.queue_declare(queue='search')


def callback(ch, method, properties, body):
    print('Received')
    data = json.loads(body)
    print(data)


    if properties.content_type == 'create_crowdfunding':
        print(type(data))
        create_crowdfunding_data = data.get('title')
        crowd_add_data = Crowd(title = create_crowdfunding_data)
        crowd_add_data.save()
        print('suc')

print('Account idx has been saved.')

channel.exchange_declare(exchange='direct')
channel.queue_bind(exchange='direct', queue='search', routing_key='create_crowdfunding')
channel.basic_consume(queue='search', on_message_callback=callback, auto_ack=True)

print('Started consuming')

channel.start_consuming()

channel.close()





