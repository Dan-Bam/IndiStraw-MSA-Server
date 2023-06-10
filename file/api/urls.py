from django.urls import path, include
from rest_framework import routers
from .views import FileUploadAPIView


# Wire up our API using automatic URL routing.
urlpatterns = [
    path('file/', FileUploadAPIView.as_view()),
]