# Blanc-Labs-Fraud - Challenge

This is a project developed like a PoC, not is a project related with no one company and only reflected my own knowledge and some knowledge used from open source repositories.

## Set up a Spring Boot app in development environment

If you are using Gradle, execute:

`./gradlew build && java -jar build/libs/gs-spring-boot-docker-0.1.0.jar`

If you are using Maven, execute:

`./mvnw package && java -jar target/gs-spring-boot-docker-0.1.0.jar`

## If you use containers

If you are using Gradle, execute:

`./gradlew build docker`

If you are using Maven, execute:

`./mvnw install dockerfile:build`

And after, use the follow script to start container in docker:

`docker run -p 8080:8080 -t blanc_labs/fraud`

---

## REST Endpoint

Get credit cards generated in a day specific:

_Request ::_

`http://localhost:8080/cc/generator?day={day}`

_Response ::_
  ```json
    {
      "day": 5,
      "numberOfCreditCards": 600
    }
  ```
