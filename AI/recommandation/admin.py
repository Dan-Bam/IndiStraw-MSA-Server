from django.contrib import admin
from .models import DefaultRecommandation, ViewRecord, GenreData, Recommandation

admin.site.register(DefaultRecommandation)
admin.site.register(ViewRecord)
admin.site.register(GenreData)
admin.site.register(Recommandation)

# Register your models here.
