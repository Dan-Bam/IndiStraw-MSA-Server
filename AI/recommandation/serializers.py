from rest_framework import serializers
from .models import *


class ViewSerializer(serializers.ModelSerializer):
    class Meta:
        model = ViewRecord
        fields = "__all__"
