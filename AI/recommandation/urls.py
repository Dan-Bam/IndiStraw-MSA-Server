from django.urls import path, include
from .views import ViewSet

urlpatterns = [
    path('', ViewSet.as_view({'get': 'list'}))
]