from models import View_Record, Genre_Data, Default_Recommandation, Recommandation
view = View_Record.objects.all()
genre = Genre_Data.objects.all()
dict = {}
for i in range(genre.count()):
    view = view.filter(record__contains='3')
    dict.i = view.count()
print(dict)
