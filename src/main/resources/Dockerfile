FROM openjdk:8-jdk-alpine

MAINTAINER "Jzj"

WORKDIR app

ADD vblog-0.0.1-SNAPSHOT.jar /app/vblog-0.0.1-SNAPSHOT.jar

ENV TIME_ZONE=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TIME_ZONE /etc/localtime && echo $TIME_ZONE >  /etc/timezone

EXPOSE 8081

CMD java -jar  -Xms512M -Xmx512M -Duser.timezone=GMT+8 /app/vblog-0.0.1-SNAPSHOT.jar
