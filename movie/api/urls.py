from django.urls import path
from .views import MovieAPIView, MovieDefailView

urlpatterns = [
    path('', MovieAPIView.as_view(), name = "movie_api"),
    path('<int:pk>', MovieDefailView.as_view(), name = "movie_api_detail")
]
