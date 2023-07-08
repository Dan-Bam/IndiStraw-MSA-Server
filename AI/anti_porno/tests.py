from django.test import TestCase

dong = [
    {"account_index": 1,
     "movie_idx": 1
     },
    {"account_index": 1,
     "movie_idx": 1
     }
]
# Create your tests here.
history = []
for i in dong:
    history.append(i["movie_idx"])
print(history)