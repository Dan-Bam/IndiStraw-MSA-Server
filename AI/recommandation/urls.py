from django.urls import path, include
from .views import *

urlpatterns = [
    path('popular/', get_popular),
    path('', get_personal_recommend),

]