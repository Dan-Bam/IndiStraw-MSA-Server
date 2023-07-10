from django.urls import path, include
from rest_framework import routers
from .views import FileUploadAPIView

urlpatterns = [
    path('file/', FileUploadAPIView.as_view(), name="file_upload"),
]