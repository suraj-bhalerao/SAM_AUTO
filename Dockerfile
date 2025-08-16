FROM maven:3.9.9-jdk-21

WORKDIR /usr/src/app

COPY . .

CMD ["mvn", "test"]
