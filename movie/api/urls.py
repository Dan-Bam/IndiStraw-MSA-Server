from django.urls import path, include
from .views import MovieViewSet, MovieDefailView
from rest_framework.routers import DefaultRouter

router = DefaultRouter()
router.register('', MovieViewSet)  

urlpatterns = [
    path('', include(router.urls)),
    path('<int:pk>/', MovieDefailView.as_view(), name = "movie_api_detail")
]
