FROM maven:3.9.9-eclipse-temurin-21

WORKDIR /usr/src/app

COPY . .

CMD ["mvn", "test"]
