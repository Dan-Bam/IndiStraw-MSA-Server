import os
import json

os.environ.setdefault("DJANGO_SETTINGS_MODULE", 'AI.settings')

import django

django.setup()
from models import ViewRecord, GenreData, DefaultRecommandation, Recommandation
import random

numbers = list(range(100, 151))

for i in range(300):
    random_numbers = random.sample((numbers, 8))
    view = ViewRecord(account_id=i, record=random_numbers)
    view.save()
print('suc')
