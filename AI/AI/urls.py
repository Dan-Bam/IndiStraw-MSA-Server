"""
URL configuration for AI project.

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/4.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path, include
from anti_porno import views as a_view
from recommandation import views

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/v1/movie/popular/', views.get_popular),
    path('api/v1/movie/recommend/', views.get_personal_recommend),
    path('api/v1/movie/porno/', a_view.check_porno),
    path('api/v1/banner/', views.get_banner)
]
