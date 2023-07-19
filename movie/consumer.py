import pika
import os, json

os.environ.setdefault("DJANGO_SETTINGS_MODULE",'movie.settings')

import django 

django.setup()

from api.models import Account 

params = pika.URLParameters('amqp://danbam0907:danbam1234!@svc.sel4.cloudtype.app:31704/')

connection = pika.BlockingConnection(params)

channel = connection.channel()

channel.queue_declare(queue='movie')

def callback(ch, method, properties, body):
    data = json.loads(body)
    
    if properties.content_type == 'create_account':
        print('Account idx has been saved.')
        create_account_data = data.get('accountIdx')
        account_add_data = Account(account_idx=create_account_data)
        account_add_data.save()

    elif properties.content_type == 'delete_account':
        print('delete account')
        create_account_data = data.get('accountIdx')
        account_delete_data = Account.objects.get(account_idx=create_account_data)
        account_delete_data.delete()

print('Account idx has been saved.')
channel.exchange_declare(exchange='direct')
channel.queue_bind(exchange='direct', queue='movie', routing_key='create_account')
channel.basic_consume(queue='movie', on_message_callback=callback, auto_ack=True)

print('Started consuming')

channel.start_consuming()

channel.close()
