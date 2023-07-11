from django.urls import path, include
from .views import *

urlpatterns = [
    path('<int:pk>/', get_popular)
]