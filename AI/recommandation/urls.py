from django.urls import path, include
from .views import *

urlpatterns = [
    path('', get_popular),
    path('<int:pk>/', get_personal_recommend),
    path('test/', test)
]