from django.urls import path, include
from .views import MovieViewSet, MovieDefailView, MovieHistoryViewSet, AccountViewSet
from rest_framework.routers import DefaultRouter
router = DefaultRouter()
router.register('', MovieViewSet)  

urlpatterns = [
    path('account/', AccountViewSet.as_view({'post':'create'}), name = "account"),
    path('movie/detail/', include(router.urls)),
    path('movie/detail/<int:pk>/', MovieDefailView.as_view(), name = "movie_api_detail"),
    path('movie/history/<int:account_index>/', MovieHistoryViewSet.as_view({'get': 'list','post' : 'create'}), name = "movie_history"),
]
