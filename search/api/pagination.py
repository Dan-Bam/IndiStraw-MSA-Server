from rest_framework.pagination import PageNumberPagination
from django.core.paginator import InvalidPage
from rest_framework.exceptions import NotFound
from rest_framework.response import Response
from collections import OrderedDict
from rest_framework.utils.urls import remove_query_param, replace_query_param

class PageNumberPagination(PageNumberPagination):

    page_size = '20'


    def paginate_queryset(self, queryset, request, view=None):
        page_size = self.get_page_size(request)
        if not page_size:
            return None
        paginator = self.django_paginator_class(queryset, page_size)
        page_number = request.query_params.get(self.page_query_param, 1)

        try:
            self.page = paginator.page(page_number)
        except InvalidPage as e:
            msg = self.invalid_page_message.format(
                page_number=page_number, message=str(e)
            )
            raise NotFound(msg)

        return list(self.page)

    def get_next_link(self):
        if not self.page.has_next():
            return True
        else :
            return False
    
    def get_paginated_response(self, data):
        return Response(OrderedDict([
            ('isLast', self.get_next_link()),
            ('list', data)
        ]))