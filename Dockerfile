# This base image is provided by Circle CI and comes with java, node and chrome
# see https://hub.docker.com/r/circleci/openjdk/
FROM circleci/openjdk:8-jdk-node-browsers

WORKDIR /usr/src/app
COPY . .

# TODO: fix permissions for running gradle and remove root user
# to provoke error remove the USER line and add --stacktrace to the build command
USER root
RUN ./gradlew build

CMD ["java", "-jar", "build/libs/movies-0.0.1-SNAPSHOT.jar"]