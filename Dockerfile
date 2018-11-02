# This base image is provided by Circle CI and comes with java, node and chrome
# see https://hub.docker.com/r/circleci/openjdk/
FROM circleci/openjdk:8-jdk-node-browsers

WORKDIR /usr/src/app
COPY . .

# needed to allow gradle execution
RUN sudo chmod 777 -R .
RUN gradle build

CMD ["java", "-jar", "build/libs/movies-0.0.1-SNAPSHOT.jar", "--server.port=$PORT"]