# talcibank-app
![Logo](https://github.com/akmal2409/talcibank-app/blob/master/talcibank-web/src/main/resources/static/images/logo.png?raw=true)
Hello! This is my 4th Spring Boot project "Talci Bank App". I am trying to build fully functioning simple online banking system from scratch.
#### Note
Web and Data are separated to structure the project better.

# Future Plans and Improvements
* Planning to integrate a database
* Do front end with Angular (First I have to learn it xD)
* Add Spring Security
* ~~Deploy using Docker
* More TBA :D

# Clone repository
#### SSH
    git@github.com:akmal2409/talcibank-app.git
#### HTTPS
    https://github.com/akmal2409/talcibank-app.git

# Building project
    mvn clean package

# Tests
#### Unit Tests (You can run with surefire plugin)
    mvn failsafe:integration-test
#### Integration Tests (You can run with failsafe plugin)
    mvn surefire:tests

# Running the project
    mvnw spring-boot:run
# Running the project in Docker
* Run command: 

    mvn clean package
* Pull Spring Boot image from Docker Hub
    
    docker run spring-boot-docker
* Extract from target folder under WEB part of the project talcibank-web-SNAPSHOT-002.jar (or .war) and place it in a folder together with Dockerfile
* Open the console in that folder
* Run command 

    docker build -t spring-boot-docker .
* Deploy your container and map port to 8080
    
    docker run -d -p 8080:8080 spring-boot-docker

  
# Technologies used so far
* Spring Framework 5
* Spring Boot
* Hibernate
* Project Lombok

# CI/CD
* Circle CI

## Prerequisites
* Java 11 or newer
* Maven
    
