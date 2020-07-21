FROM openjdk:8
ADD demo-store-api.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container","-DDATABASE_HOST=db","-DDATABASE_PORT=3306","-DDATABASE_NAME=store","-DDATABASE_USER=root","-DDATABASE_PASSWORD=root","-jar","app.jar"]