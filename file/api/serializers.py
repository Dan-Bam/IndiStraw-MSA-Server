from rest_framework.serializers import Serializer, FileField, ListField

class UploadSerializer(Serializer):
    file_uploaded = FileField()
    class Meta:
        fields = ['file_uploaded']
