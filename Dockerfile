FROM springcloud/baseimage:1.0.0

RUN apt-get update && apt-get install -y locales tzdata
RUN locale-gen ko_KR.UTF-8
ENV LANG ko_KR.UTF-8
ENV LANGUAGE ko_KR.UTF-8
ENV LC_ALL ko_KR.UTF-8
RUN localedef -f UTF-8 -i ko_KR ko_KR.utf8
RUN update-locale LANG=ko_KR.UTF-8
