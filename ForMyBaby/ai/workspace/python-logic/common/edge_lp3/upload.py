import boto3
import os
from botocore.exceptions import ClientError

# AWS S3 관련 정보 설정
BUCKET_NAME = 'formybaby-bucket'  # 본인의 S3 버킷 이름으로 변경해주세요.
AWS_REGION = 'ap-northeast-2'  # 본인의 AWS 리전 정보로 변경해주세요.
ACCESS_KEY = 'AKIA2UC3BWP55UET3D4E'
SECRET_KEY = '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'

    
def uploadS3(FILE_NAME, file_path, folder_name):
    # S3 클라이언트 생성
    s3 = boto3.client('s3', aws_access_key_id=ACCESS_KEY, aws_secret_access_key=SECRET_KEY)

    # 폴더가 있는지 확인하고 없으면 생성
    try:
        s3.head_object(Bucket=BUCKET_NAME, Key=f"{folder_name}/")
    except ClientError as e:
        if e.response['Error']['Code'] == '404':
            s3.put_object(Bucket=BUCKET_NAME, Key=f"{folder_name}/")

     # 파일 업로드
    with open(file_path, 'rb') as file:
        s3.upload_fileobj(file, BUCKET_NAME, f"{folder_name}/{FILE_NAME}")

    # 업로드된 파일의 URL 생성
    url = f"https://{BUCKET_NAME}.s3.amazonaws.com/{folder_name}/{FILE_NAME}"
    print("Uploaded file URL:", url)
    
    return url
