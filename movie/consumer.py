import pika
import os, json

os.environ.setdefault("DJANGO_SETTINGS_MODULE",'movie.settings')

import django 

django.setup()

from api.models import Account

params = pika.URLParameters('amqps://igqylvwy:TcwMgVG-nqWB4Riz7lSMPp17hEg3qOAC@vulture.rmq.cloudamqp.com/igqylvwy')

connection = pika.BlockingConnection(params)

channel = connection.channel()

channel.queue_declare(queue='movie')

def callback(ch, method, properties, body):
    data = json.loads(body)
    print(data)
    
    if properties.content_type == 'create_account':
        account = Account.query.get(data['id'])
        account.account_idx = data['account_idx']
        account.save()
        print('Account idx has been saved.')

channel.basic_consume(queue='movie', on_message_callback=callback, auto_ack=True)

print('Started consuming')

channel.start_consuming()

channel.close()
