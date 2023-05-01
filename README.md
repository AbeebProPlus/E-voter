## API Documentation - Registration Service

This API provides registration service for candidates and non-candidates for a voting system.

### Candidate Registration API

#### Register Candidate

Registers a new candidate with the provided information.

**Request**
- URL: `/register/candidate`
- Method: `POST`
- Headers:
  - `Content-Type: application/json`
- Body:

  ```
  {
      "firstName": "John",
      "lastName": "Doe",
      "email": "johndoe@example.com"
  }
  ```

**Response**
- Status: `201 Created`
- Headers:
  - `Content-Type: application/json`
- Body:

  ```
  {
      "data": {
          "firstName": "John",
          "lastName": "Doe",
          "email": "johndoe@example.com",
          "id": 1,
          "noOfVotes": 0
      },
      "statusCode": "CREATED",
      "message": "Candidate registered successfully.",
      "timeStamp": "2023-05-01T12:00:00Z",
      "path": "/register/candidate",
      "isSuccessful": true
  }
  ```

#### Exceptions
- 400 Bad Request:
  - UserExistsException: If the user already exists.

### Non-Candidate Registration API

#### Register Non-Candidate

Registers a new non-candidate with the provided information.

**Request**
- URL: `/register/non-candidate`
- Method: `POST`
- Headers:
  - `Content-Type: application/json`
- Body:

  ```
  {
      "firstName": "Jane",
      "lastName": "Doe",
      "email": "janedoe@example.com"
  }
  ```

**Response**
- Status: `201 Created`
- Headers:
  - `Content-Type: application/json`
- Body:

  ```
  {
      "data": {
          "firstName": "Jane",
          "lastName": "Doe",
          "email": "janedoe@example.com",
          "id": 1
      },
      "statusCode": "CREATED",
      "message": "Non-candidate registered successfully.",
      "timeStamp": "2023-05-01T12:00:00Z",
      "path": "/register/non-candidate",
      "isSuccessful": true
  }
  ```

#### Exceptions
- 400 Bad Request:
  - UserExistsException: If the user already exists.

### Models

#### Candidate

```
{
    "id": Long,
    "firstName": String,
    "lastName": String,
    "email": String,
    "noOfVotes": Long
}
```

#### Non-Candidate

```
{
    "id": Long,
    "firstName": String,
    "lastName": String,
    "email": String
}
```

### Exceptions

#### UserExistsException

This exception is thrown when a user with the same email already exists.

```
{
    "status": "BAD_REQUEST",
    "message": "User already exists with email: johndoe@example.com"
}
###Configuration properties
spring.application.name=registration-service
spring.cloud.config.enabled=false
server.port=5555
eureka.client.serviceUrl.defaultZone=http://localhost:7778/eureka
```
############################################################################################################################################################3333
# API Readme - Email Sender Service
This API provides functionality to send an email with and without attachments. It is implemented using JavaMailSender and Spring Boot.

# Getting Started
To use this API, you need to have Java installed on your computer. You will also need to set up an email account to use as the sender.

# Prerequisites
-	Java 8 or higher
-	Email account to use as the sender

# Installation
1.	Clone the repository.
2.	Set the following environment variables:
3.	EMAIL - The email address of the sender.
4.	PASSWORD - The password for the email account.
5.	Run the application using your preferred IDE or using the following command:
arduino


./mvnw spring-boot:run
API Endpoints
The following endpoints are available:

#Send email
This endpoint sends an email.

URL: /api/email/send

Method: POST

Request Body:
{
  "to": "example@example.com",
  "from": "example@example.com",
  "subject": "Test email",
  "message": "Hello, this is a test email."
}
Response Body:


{
  "httpStatus": "OK",
  "message": "Email sent successfully to example@example.com"
}
# Configuration
The following configuration options are available:

-  spring.mail.host=smtp.gmail.com
-  spring.mail.username=${EMAIL}
-  spring.mail.password=${PASSWORD}
-  spring.mail.port=587
-  spring.mail.properties.mail.smtp.auth=true
-  spring.mail.properties.mail.smtp.starttls.enable=true

##################################################################################################################################################################################
#AppGatewayConfiguration
This class is responsible for configuring the routes for the API gateway.
#Methods
##routerLocator
@Bean 
public RouteLocator routerLocator(RouteLocatorBuilder locatorBuilder) 
This method returns a RouteLocator that defines the routes for the API gateway. It takes a RouteLocatorBuilder as an argument, which is used to build the routes.
Arguments
locatorBuilder - The RouteLocatorBuilder to use for building the routes.
Returns
A RouteLocator that defines the routes for the API gateway.
#Configuration Properties
spring.application.name=api-gateway -- This property sets the name of the application
server.port = 7777 -- This property sets the port on which the server will listen.
eureka.client.serviceUrl.defaultZone= -http://localhost:7778/eureka (This property sets the default Eureka service URL)

###############################################################################################################################################################3
#Service-registry
# Configuration properties
spring.cloud.config.enabled=false
server.port=7778
spring.application.name=registry-service
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false

###spring.cloud.config.enabled=false: This line disables the Spring Cloud Config client. If Spring Cloud Config is not being used to manage the application's configuration properties, this line can be used to turn off the client.
###server.port=7778: This line sets the port on which the application will listen for incoming HTTP requests. In this case, the port is set to 7778.
###spring.application.name=registry-service: This line sets the name of the Spring Boot application. The application name is used in various places, including registering with a service registry like Eureka.
###eureka.client.register-with-eureka=false: This line disables the registration of the application with a service registry like Eureka. If service registration is not needed, this line can be used to turn off registration.
###eureka.client.fetch-registry=false: This line disables fetching the registry of registered services from the service registry. If service discovery is not needed, this line can be used to turn off fetching the registry.

##################################################################################################################################################################33


# Voting Service API

This is a RESTful API that allows users to cast their votes for a candidate, either as a registered candidate or non-registered candidate.

The following are the endpoints exposed by the API:

### Endpoint 1: Vote for a candidate

This endpoint allows a registered candidate to cast their vote for another candidate.

```http
POST /vote/candidate
```

**Request Parameters**

The request body should be a JSON object with the following properties:

- `candidateId` - the ID of the candidate being voted for
- `token` - the token of the registered candidate casting the vote

Example request body:

```json
{
    "candidateId": 123,
    "token": "some-token"
}
```

**Response**

The API returns a JSON object with the following properties:

- `data` - the response from the API
- `statusCode` - the HTTP status code returned by the API
- `isSuccessful` - a boolean indicating whether the request was successful or not
- `timeStamp` - the timestamp of the response
- `path` - the API endpoint path

Example response body:

```json
{
    "data": {
        "httpStatus": "OK",
        "message": "You have voted successfully"
    },
    "statusCode": 200,
    "isSuccessful": true,
    "timeStamp": "2023-05-01T10:00:00.000Z",
    "path": "/vote/candidate"
}
```

### Endpoint 2: Vote for a non-candidate

This endpoint allows a non-registered candidate to cast their vote for a registered candidate.

```http
POST /vote/non-candidate
```

**Request Parameters**

The request body should be a JSON object with the following properties:

- `candidateId` - the ID of the candidate being voted for
- `voterEmail` - the email of the non-registered candidate casting the vote
- `token` - the token of the non-registered candidate casting the vote

Example request body:

```json
{
    "candidateId": 123,
    "voterEmail": "john.doe@example.com",
    "token": "some-token"
}
```

**Response**

The API returns a JSON object with the following properties:

- `data` - the response from the API
- `statusCode` - the HTTP status code returned by the API
- `isSuccessful` - a boolean indicating whether the request was successful or not
- `timeStamp` - the timestamp of the response
- `path` - the API endpoint path

Example response body:

```json
{
    "data": {
        "httpStatus": "OK",
        "message": "You have voted successfully"
    },
    "statusCode": 200,
    "isSuccessful": true,
    "timeStamp": "2023-05-01T10:00:00.000Z",
    "path": "/vote/non-candidate"
}
```

### Error Handling

The API returns appropriate error messages for invalid requests or internal server errors. The errors are returned as a JSON object with a `message` property containing a brief description of the error.

Example error response body:

```json
{
    "message": "Invalid or used token"
}
```

### Libraries and Frameworks Used

- Spring Boot
- Feign Client
- Lombok
- Mindrot JBCrypt

###########################################################################################################################################################################

# Data Service

Data Service is a REST API service that handles registration of voters and validation of their information.
### Prerequisites

Before you can run this project, make sure you have the following installed on your local machine:

- JDK 1.8 or higher
- Maven 3.2 or higher

## API Endpoints

The following API endpoints are available:

- POST /data/register-candidate - registers a candidate voter
- POST /data/register-non-candidate - registers a non-candidate voter
- POST /data/validateCandidateEmail - validates if the email is already used by a candidate
- POST /data/validateNonCandidateEmail - validates if the email is already used by a non-candidate
- POST /data/candidateId - finds a candidate by id
# Data Service API

This API provides endpoints to register and validate candidates and non-candidates.

## Endpoints

### Data Controller

#### POST /data/register-candidate

Register a new candidate.

Request Body:
```json
{
  "firstName": "string",
  "lastName": "string",
  "token": "string",
  "email": "string",
  "noOfVotes": 0
}
```

#### POST /data/register-non-candidate

Register a new non-candidate.

Request Body:
```json
{
  "firstName": "string",
  "lastName": "string",
  "token": "string",
  "email": "string"
}
```

#### POST /data/validateCandidateEmail

Check if an email has been used by a candidate.

Request Body:
```json
"string"
```

#### POST /data/validateNonCandidateEmail

Check if an email has been used by a non-candidate.

Request Body:
```json
"string"
```

#### POST /data/candidateId

Get candidate by id.

Request Body:
```json
0
```

## Models

### Candidate

```json
{
  "id": 0,
  "firstName": "string",
  "lastName": "string",
  "token": "string",
  "email": "string",
  "noOfVotes": 0
}
```

### NonCandidate

```json
{
  "id": 0,
  "firstName": "string",
  "lastName": "string",
  "token": "string",
  "email": "string"
}
```

### Voter

```json
{
  "firstName": "string",
  "lastName": "string",
  "token": "string",
  "email": "string"
}
```

## Repositories

### CandidateRepo

```java
public interface CandidateRepo extends JpaRepository<Candidate, Long> {
    Candidate findByEmail(String email);
    Optional<Candidate> findById(Long id);
}
```

### NonCandidateRepo

```java
public interface NonCandidateRepo extends JpaRepository<NonCandidate, Long> {
    NonCandidate findByEmail(String email);
}
```

## Services

### CandidateService

```java
public interface CandidateService {
    void saveCandidate(Candidate candidate);
    Candidate checkIfMailHasBeenUsedByCandidate(String email);
    Candidate findCandidateById(Long id);
}
```

### NonCandidateService

```java
public interface NonCandidateService {
    void saveNonCandidate(NonCandidate nonCandidate);
    NonCandidate checkIfMailHasBeenUsedByNonCandidate(String email);
}
```

## Configuration

- Server port: 7770
- Spring H2 Console enabled
- Spring datasource username: user
- Spring datasource password: user
- Spring datasource URL: jdbc:h2:mem:testdb
- Spring application name: data-service
- Spring datasource driverClassName: org.h2.Driver
- Spring JPA database platform: org.hibernate.dialect.H2Dialect
- Spring JPA DDL auto: create-drop
- Eureka client service URL: http://localhost:7778/eureka
## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [H2 Database](https://www.h2database.com/html/main.html) - In-memory database


