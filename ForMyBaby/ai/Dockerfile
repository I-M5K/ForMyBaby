# FROM python:3.9
FROM node:21.7.1-alpine
FROM ubuntu:jammy

# WORKDIR /AI/workspace/python-logic/common/edge_lp3
WORKDIR /python
COPY ./workspace/python-logic/common/edge_lp3 .

# 필요한 Python 패키지를 직접 설치하는 경우에는 아래와 같이 명령어를 사용합니다.
RUN apt update && apt install -y python3 python3-pip
# RUN apk update && apk add --no-cache \
#     python3-dev \
#     py3-pip \
#     gcc \
#     libc-dev
RUN pip install \
    catkin-pkg==0.4.23 \
    certifi==2024.2.2 \
    cffi==1.15.1 \
    charset-normalizer==3.3.2 \
    click==8.1.7 \
    colcon-cmake==0.2.28 \
    colcon-common-extensions==0.3.0 \
    colcon-core==0.15.2 \
    colcon-defaults==0.2.8 \
    colcon-devtools==0.2.5 \
    colcon-library-path==0.2.1 \
    colcon-metadata==0.2.5 \
    colcon-notification==0.2.15 \
    colcon-output==0.2.13 \
    colcon-package-information==0.3.3 \
    colcon-package-selection==0.2.10 \
    colcon-parallel-executor==0.3.0 \
    colcon-pkg-config==0.1.0 \
    colcon-powershell==0.4.0 \
    colcon-python-setup-py==0.2.8 \
    colcon-recursive-crawl==0.2.3 \
    colcon-ros==0.4.1 \
    colcon-test-result==0.3.8 \
    colorama==0.4.6 \
    coloredlogs==15.0.1 \
    coverage==7.2.7 \
    cryptography==3.4.7 \
    distlib==0.3.8 \
    dlib==19.24.2 \
    docutils==0.20.1 \
    empy==3.3.4 \
    exceptiongroup==1.2.0 \
    face-recognition==1.3.0 \
    face-recognition-models==0.3.0 \
    gevent==22.10.2 \
    greenlet==3.0.3 \
    humanfriendly==10.0 \
    idna==3.6 \
    ifcfg==0.21 \
    importlib-metadata==6.7.0 \
    iniconfig==2.0.0 \
    lark-parser==0.11.3 \
    lxml==4.6.3 \
    netifaces==0.11.0 \
    node==1.2.1 \
    nodejs==0.1.1 \
    npm==0.1.1 \
    numpy==1.21.1 \
    odict==1.9.0 \
    opencv-contrib-python==4.5.3.56 \
    opencv-python==4.5.3.56 \
    optional-django==0.1.0 \
    packaging==20.4 \
    Pillow==9.5.0 \
    pluggy==1.2.0 \
    plumber==1.7 \
    pycparser==2.20 \
    pydot==1.4.2 \
    pyparsing==3.1.1 \
    PyQt5==5.15.4 \
    PyQt5-Qt5==5.15.2 \
    PyQt5-sip==12.13.0 \
    pyqtgraph==0.12.2 \
    pyreadline==2.1 \
    PySide2==5.15.2 \
    pytest==7.4.4 \
    pytest-cov==4.1.0 \
    pytest-repeat==0.9.3 \
    pytest-rerunfailures==13.0 \
    python-dateutil==2.8.2 \
    python-engineio==3.14.2 \
    python-engineio-client==1.1 \
    python-socketio==4.6.0 \
    python-socketio-client==1.1 \
    pywin32==306 \
    PyYAML==6.0.1 \
    requests==2.31.0 \
    scipy==1.7.3 \
    shiboken2==5.15.2 \
    six==1.16.0 \
    sounddevice==0.4.6 \
    squaternion==0.3.1 \
    tomli==2.0.1 \
    tqdm==4.66.2 \
    typing_extensions==4.7.1 \
    urllib3==2.0.7 \
    websockets==11.0.3 \
    zipp==3.15.0 \
    zope.component==6.0 \
    zope.deferredimport==5.0 \
    zope.deprecation==5.0 \
    zope.event==5.0 \
    zope.hookable==6.0 \
    zope.interface==6.2 \
    zope.lifecycleevent==5.0 \
    zope.proxy==5.2

# RUN ls -al*
# Run pwd*
CMD ["python3", "main.py"]

# WORKDIR /AI/workspace/express-network
WORKDIR /node
COPY ./workspace/express-network .
RUN npm install
CMD ["node", "index.js"]


# Docker 컨테이너에서 노출할 포트를 지정합니다.
EXPOSE 3001
EXPOSE 3002
