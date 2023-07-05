from django.urls import path, include
from .views import SearchViewSet
from rest_framework.routers import DefaultRouter

router = DefaultRouter()
router.register(r'search', SearchViewSet)  

urlpatterns = [
    path('', include(router.urls))
]
