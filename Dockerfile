FROM openjdk:8-jdk

# Install node
RUN set -x \
    && curl -sL https://deb.nodesource.com/setup_8.x | bash - \
    && apt-get install -y \
        nodejs \
    && npm install -g npm@latest

WORKDIR /app
COPY . .
RUN ./gradlew build

CMD ["java", "-jar", "build/libs/movies-0.0.1-SNAPSHOT.jar"]