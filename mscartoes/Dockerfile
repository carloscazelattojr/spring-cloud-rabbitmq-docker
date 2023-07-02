FROM maven:3.8.5-openjdk-11 as buildImage
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:11
WORKDIR /app
COPY --from=buildImage ./app/target/*.jar ./app.jar
ARG RABBITMQ_SERVER=ms-credito-rabbitmq
ARG EUREKA_SERVER=ms-credito-eurekaserver
ENTRYPOINT java -jar app.jar