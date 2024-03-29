FROM python:3.9

WORKDIR /AI/express-network/node_modules/.bin:$PATH

# 필요한 Python 패키지가 있다면 requirements.txt를 이용하여 설치할 수 있습니다.
# COPY requirements.txt .
# RUN pip install -r requirements.txt

COPY . .

# 필요한 Python 패키지를 직접 설치하는 경우에는 아래와 같이 명령어를 사용합니다.
# RUN pip install 패키지이름

# 필요한 Python 패키지가 설치되었다고 가정하고, 서버를 실행하는 CMD를 지정합니다.
CMD ["python", "start"]

# Docker 컨테이너에서 노출할 포트를 지정합니다.
EXPOSE 3002
