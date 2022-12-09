FROM openjdk:8-jdk-alpine

RUN apk add --update --no-cache ttf-dejavu fontconfig && rm -rf /var/cache/apk/*


MAINTAINER "Jzj"

WORKDIR app

ADD vblog.jar /app/vblog.jar

ENV TIME_ZONE=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TIME_ZONE /etc/localtime && echo $TIME_ZONE >  /etc/timezone

EXPOSE 8081

CMD java -jar  -Xms512M -Xmx512M -Duser.timezone=GMT+8 /app/vblog.jar
