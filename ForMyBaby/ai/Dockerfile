# 베이스 이미지로 node와 python을 포함하는 이미지를 사용합니다.
FROM node:21.7.1-alpine

# 필요한 패키지를 설치하기 위해 apk를 업데이트하고 python3와 pip를 설치합니다.
RUN apk update && apk add --no-cache python3 python3-dev py3-pip

# Python 애플리케이션에 필요한 파일을 복사합니다.
WORKDIR /app/python-logic/common/edge_lp3
COPY ./workspace/python-logic/common/edge_lp3/main.py /app/python-logic/common/edge_lp3/main.py
COPY ./workspace/python-logic/common/edge_lp3/requirements.txt /app/python-logic/common/edge_lp3/requirements.txt

# Python 의존성을 설치합니다.
RUN pip install -r /app/python-logic/common/edge_lp3/requirements.txt

# Node.js 애플리케이션에 필요한 파일을 복사합니다.
WORKDIR /app/express-network
COPY ./workspace/express-network/index.js /app/express-network/index.js

# Node.js 패키지를 설치합니다.
RUN npm install

# 애플리케이션 실행을 위해 적절한 디렉토리로 이동합니다.
WORKDIR /app

# 실행할 명령을 지정합니다.
CMD ["node", "express-network/index.js"]
