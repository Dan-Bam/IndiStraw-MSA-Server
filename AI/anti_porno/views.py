import os, jwt, requests
from django.shortcuts import render
from google.cloud import videointelligence, storage, storage_transfer
import boto3, environ, json
from datetime import datetime
today = datetime.now()
env = environ.Env(
    # set casting, default value
    DEBUG=(bool, False)
)
from rest_framework.decorators import api_view
import googleapiclient.discovery

"""Detects explicit content from the GCS path to a video."""
from rest_framework.response import Response
@api_view(['POST'])
def check_porno(req):
    key = req.headers.get('Authorization')
    payload = jwt.decode(jwt=key[7:], key=os.environ.get('JWT_SECRET_KEY'), algorithms='HS256')
    s3 = boto3.client('s3')
    storagetransfer = googleapiclient.discovery.build('storagetransfer', 'v1')
    transfer_job = {
        'description': 'Sync data from AWS to GCP',
        'status': 'ENABLED',
        'projectId': 'indisraw',
        'transferSpec': {
            'awsS3DataSource': {
                'bucketName': os.environ.get('AWS_STORAGE_BUCKET_NAME'),
                'awsAccessKey': {
                    'accessKeyId': os.environ.get('AWS_ACCESS_KEY_ID'),
                    'secretAccessKey': os.environ.get('AWS_SECRET_ACCESS_KEY')
                }
            },
            'gcsDataSink': {
                'bucketName': 'indiestraw-bucket'
            },
            'transferOptions': {
                'deleteObjectsFromSourceAfterTransfer': False
            }
        },
        'schedule': {
            'scheduleStartDate': {
                'year': today.year,
                'month': today.month,
                'day': today.day
            },
            'scheduleEndDate': {
                'year': today.year,
                'month': today.month,
                'day': today.day
            }
        }
    }

    result = storagetransfer.transferJobs().create(body=transfer_job).execute()
    print('Returned transferJob: {}'.format(json.dumps(result, indent=4)))
    # _data_file = req.FILES['movie']
    # path = 'workspace/data.mp4'
    # default_storage.save(path, _data_file)
    # if default_storage.exists(path):
    #     print('save suc')
    # bucket_name = "indiestraw-bucket"
    # source_file_name = f"{os.path.dirname(__file__)}/static/workspace/data.mp4"
    # print(source_file_name)
    #
    # # The ID of your GCS object
    # destination_blob_name = req.data['movie_name']
    #
    # """Uploads a file to the bucket."""
    #
    # storage_client = storage.Client()
    # bucket = storage_client.bucket(bucket_name)
    # blob = bucket.blob(destination_blob_name)
    #
    # # Optional: set a generation-match precondition to avoid potential race conditions
    # # and data corruptions. The request to upload is aborted if the object's
    # # generation number does not match your precondition. For a destination
    # # object that does not yet exist, set the if_generation_match precondition to 0.
    # # If the destination object already exists in your bucket, set instead a
    # # generation-match precondition using its generation number.
    # generation_match_precondition = 0
    #
    # blob.upload_from_filename(source_file_name, if_generation_match=generation_match_precondition)
    #
    # print(
    #     f"File {source_file_name} uploaded to {destination_blob_name}."
    # )
    # default_storage.delete(path)

    video_client = videointelligence.VideoIntelligenceServiceClient()
    features = [videointelligence.Feature.EXPLICIT_CONTENT_DETECTION]
    print(features)
    operation = video_client.annotate_video(
        request={"features": features, f"input_uri": f"gs://indiestraw-bucket/{req.data['Url']}"}
    )
    print("\nProcessing video for explicit content annotations:")

    result = operation.result(timeout=90)
    print("\nFinished processing.")
    a = {'VERY_UNLIKELY': 0, 'UNLIKELY': 0, 'POSSIBLE': 0, 'LIKELY': 0, 'VERY_LIKELY': 0}
    # Retrieve first result because a single video was processed
    frame_time = 0
    for frame in result.annotation_results[0].explicit_annotation.frames:
        likelihood = videointelligence.Likelihood(frame.pornography_likelihood)
        frame_time = frame.time_offset.seconds + frame.time_offset.microseconds / 1e6
        # print("Time: {}s".format(frame_time))
        # print("\tpornography: {}".format(likelihood.name))
        a[likelihood.name] += 1
    if frame_time / 3 < (a['VERY_LIKELY'] + a['LIKELY']):
        requests.get(f'http://52.79.249.14:8001/movie/delete_porno/{req.data["Url"]}/')
        return Response(payload['sub']+"This is porno")
    else:
        return Response(    "This is not porno")


from django.shortcuts import render

# Create your views here.
