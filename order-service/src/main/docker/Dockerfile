FROM java:8-jre-alpine
MAINTAINER Paweł Żeszko

ADD @project.build.finalName@.jar app.jar

RUN sh -c 'touch /app.jar'
CMD ["java", "$JAVA_OPTS", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "-jar", "/app.jar"]