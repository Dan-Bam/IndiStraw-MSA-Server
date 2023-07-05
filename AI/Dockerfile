FROM python:3.9
ENV PYTHONUNBUFFERED 1
WORKDIR /AI
COPY requirements.txt /AI/requirements.txt
RUN pip install -r requirements.txt
RUN export GOOGLE_APPLICATION_CREDENTIALS="indiestraw.json"
COPY .. /AI