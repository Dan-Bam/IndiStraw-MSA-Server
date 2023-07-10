import os
import json

os.environ.setdefault("DJANGO_SETTINGS_MODULE", 'AI.settings')

import django

django.setup()
from recommandation.models import ViewRecord, GenreData, DefaultRecommandation, Recommandation
import random

numbers = list(range(100, 151))

import random

genres = [
    '액션', '로맨스', '코미디', '스릴러', '드라마',
    'SF', '애니메이션', '공포', '모험', '판타지'
]

for i in range(100,151):
    selected_genres = random.sample(genres, 3)
    view = GenreData(movie_id=i, genre=selected_genres)
    view.save()
print('suc')
