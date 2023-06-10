from rest_framework import response, status
from .serializers import UploadSerializer
from rest_framework.generics import ListCreateAPIView
from .serializers import UploadSerializer
from file.settings import AWS_S3_CUSTOM_DOMAIN
from .models import File
class FileUploadAPIView(ListCreateAPIView):
    serializer_class = UploadSerializer
    queryset = File.objects.all()

    def post(self, request):
        request.data.__setitem__("video_url", f"https://{AWS_S3_CUSTOM_DOMAIN}/{request.data['file']}")
        serializer = self.serializer_class(data=request.data)

        if serializer.is_valid():
            serializer.save()
            return response.Response(serializer.data, status=status.HTTP_201_CREATED)
        return response.Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def get(self, request, *args, **kwargs):
        return self.list(request, *args, **kwargs)