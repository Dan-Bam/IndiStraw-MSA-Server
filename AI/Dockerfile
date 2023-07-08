FROM python:3.9
ENV PYTHONUNBUFFERED 1
WORKDIR /AI
COPY requirements.txt /AI/requirements.txt
RUN pip install -r requirements.txt
RUN export GOOGLE_APPLICATION_CREDENTIALS="indiestraw.json"
RUN export DJANGO_SETTINGS_MODULE="AI.settings"
COPY . /AI