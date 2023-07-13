import os
import json

os.environ.setdefault("DJANGO_SETTINGS_MODULE", 'AI.settings')

import django

django.setup()
from models import ViewRecord, GenreData, Recommandation
import random
import jwt
numbers = list(range(100, 151))

dong = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2YzQ2MDdlYS01ZjAxLTRlNDItYTgzNS02MDJlMTcwNjJjZGYiLCJ0b2tlblR5cGUiOiJhY2Nlc3MiLCJhdXRob3JpdHkiOiJST0xFX0FDQ09VTlQiLCJpYXQiOjE2ODkyNTkxNTIsImV4cCI6MTY4OTI2MDk1Mn0.bYs58eIXyCj3SrTqFmuErx5qP4wPpFbe-Mbu38wPr_c"
jwt.decode(dong, os.environ.get(''))
for i in range(300):
    random_numbers = random.sample((numbers, 8))
    # view = ViewRecord(account_id=i, record=random_numbers)
    # view.save()
    view = ViewRecord.objects.all()
    view.delete()
print('suc')
