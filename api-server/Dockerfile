FROM openjdk:8
COPY ./target/*.jar /usr/src/app/app.jar
WORKDIR /usr/src/app
EXPOSE 9090
CMD ["java", "-Xmx200m", "-jar", "app.jar"]