from django.urls import path, include
from .views import MovieView, MovieDefailView, MovieHistoryViewSet, ActorViewSet, ActorDefailView, DirectorViewSet, DirectorDefailView


urlpatterns = [
    path('movie/history/', MovieHistoryViewSet.as_view({'get': 'list',
                                                        'post' : 'create',
                                                        'put' : 'update',
                                                        'delete' : 'destroy'}), name = "movie_history"),
    path('movie/actor/', ActorViewSet.as_view({'get':'list',
                                                'post': 'create'}), name = "actor"),
    path('movie/actor/<int:pk>/', ActorDefailView.as_view(), name = "actor_detail"),
    path('movie/director/', DirectorViewSet.as_view({'get':'list',
                                                'post': 'create'}), name = "director"),
    path('movie/director/<int:pk>/', DirectorDefailView.as_view(), name = "director_detail"),
    path('movie/<int:pk>/', MovieDefailView.as_view(), name = "movie_api_detail"),
    path('movie/', MovieView.as_view({'get':'list', 'post':'create'}),),
]
