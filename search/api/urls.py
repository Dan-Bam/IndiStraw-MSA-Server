from django.urls import path, include
from .views import SearchViewSet, SearchTagViewSet, MovieSearchViewSet
from rest_framework.routers import DefaultRouter

router = DefaultRouter()
router.register(r'', SearchViewSet)  

urlpatterns = [
    path('search/tag', SearchTagViewSet.as_view({'get':'list'})),
    path('search/movie', MovieSearchViewSet.as_view({'get':'list'})),
    path('search/', include(router.urls)),
]
