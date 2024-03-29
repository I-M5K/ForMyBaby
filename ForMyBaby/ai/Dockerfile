# FROM python:3.9
FROM node:21.7.1-alpine
FROM ubuntu:jammy

# WORKDIR /AI/workspace/python-logic/common/edge_lp3
WORKDIR /python
COPY AI/workspace/python-logic/common/edge_lp3 .

# 필요한 Python 패키지가 있다면 requirements.txt를 이용하여 설치할 수 있습니다.
# COPY ./python-logic/common/edge_ino\sensors/edge_lp3/requirements.txt .
# COPY . .
RUN apt update && apt install -y python3 python3-pip
# RUN apk update && apk add --no-cache \
#     python3-dev \
#     py3-pip \
#     gcc \
#     libc-dev
RUN pip install -r ./requirements.txt
RUN ls -al
Run pwd
CMD ["python3", "main.py"]

# WORKDIR /AI/workspace/express-network
WORKDIR /node
COPY /AI/workspace/express-network .
RUN npm install
CMD ["node", "index.js"]


# Docker 컨테이너에서 노출할 포트를 지정합니다.
EXPOSE 3001
EXPOSE 3002