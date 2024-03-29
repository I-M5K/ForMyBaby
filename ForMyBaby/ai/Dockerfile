FROM python:3.9
FROM node:21.7.1-alpine

WORKDIR /AI/workspace/python-logic/edge_lp3

# 필요한 Python 패키지가 있다면 requirements.txt를 이용하여 설치할 수 있습니다.
# COPY ./python-logic/common/edge_ino\sensors/edge_lp3/requirements.txt .
COPY . .
RUN apt-get update && apt-get install -y python3-pip
RUN pip install -r ./requirements.txt
CMD ["python3", "main.py"]

WORKDIR /AI/workspace/express-network
COPY . .
RUN npm install
CMD ["npm", "start"]


# Docker 컨테이너에서 노출할 포트를 지정합니다.
EXPOSE 3001
EXPOSE 3002
