import json

from django.test import TestCase

dong = b'"[\n    {\n        "account_index": 1,\n        "movie_idx": 1\n    },\n    {\n        "account_index": 1,\n        "movie_idx": 1\n    },\n    {\n        "account_index": 1,\n        "movie_idx": 1\n    },\n    {\n        "account_index": 1,\n        "movie_idx": 1\n    },\n    {\n        "account_index": 1,\n        "movie_idx": 1\n    },\n    {\n        "account_index": 1,\n        "movie_idx": 1\n    }\n]"'
# Create your tests here.
ding = json.loads(dong)
history = []
for i in ding:
    print(i)
print(history)