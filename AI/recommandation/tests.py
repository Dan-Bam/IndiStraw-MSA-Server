from models import ViewRecord, GenreData, DefaultRecommandation, Recommandation
view = ViewRecord.objects.all()
genre = GenreData.objects.all()
dict = {}
for i in range(genre.count()):
    view = view.filter(record__contains='3')
    dict.i = view.count()
print(dict)
