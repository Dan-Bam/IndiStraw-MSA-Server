from rest_framework import serializers
from .models import *

class TestSeriaizer(serializers.ModelSerializer):
    class Meta:
        model = Test
        fields = '__all__'