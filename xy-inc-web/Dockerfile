FROM openjdk:8-jre-alpine

ENV APP_TARGET target
ENV APP xy-inc-web.jar

RUN mkdir -p /opt
COPY ${APP_TARGET}/${APP} /opt

CMD java -Dspring.profiles.active=${SPRING_PROFILES:-qa},postgresql ${JAVA_OPTS} -Xms${JAVA_XMS:-512m} -Xmx${JAVA_XMX:-1024m} -jar /opt/${APP}