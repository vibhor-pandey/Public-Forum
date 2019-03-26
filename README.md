# Public-Forum
Public Forum

This project is the basic implementation of Posting Articles, Posting Comments in an Article and to get the List of Articles

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
1) MYSQL DATABASE
2) IDE (Eclipse / IntelliJ)
3) JAVA
```

### Changes required before running the project

Make below changes in application.properties

```
spring.datasource.username= <DB_USERNAME>
spring.datasource.password= <PASSWORD>

```


### How To Run the Project


```
To Run, use the given Command in the root path of the Project: 

./mvnw spring-boot:run
```

### Postman Link for the APIs

```
https://www.getpostman.com/collections/003abff5e128539204c1
```

### APIs 

```
1) SignUp
2) Login 
3) Post Article by Email
4) Post Comment By Article ID and User ID
5) Get Other User's Articles
6) Get Own Articles
```

### Pre-requisites for Testing

```
Eclipse: EclEmma Tool (for JUnit Testing)
```
