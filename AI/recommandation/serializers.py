from rest_framework import serializers
from .models import *


class ViewSerializer(serializers.ModelSerializer):
    class Meta:
        model = View_Record
        fields = "__all__"
