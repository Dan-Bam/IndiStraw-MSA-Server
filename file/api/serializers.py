from rest_framework.serializers import ModelSerializer
from .models import File

class UploadSerializer(ModelSerializer):
    class Meta:
        model = File
        fields = ['file']
