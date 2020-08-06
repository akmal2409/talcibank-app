# talcibank-app
![Logo](https://github.com/akmal2409/talcibank-app/blob/master/talcibank-web/src/main/resources/static/images/logo.png?raw=true)
Hello! This is my 4th Spring Boot project "Talci Bank App". I am trying to build fully functioning simple online banking system from scratch.
#### Note
Web and Data are separated to structure the project better.

# Future Plans and Improvements
* Planning to integrate a database
* Do front end with Angular (First I have to learn it xD)
* Add Spring Security
* Deploy using Docker
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
    
