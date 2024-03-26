# Reward Point Service Application

This is a Spring Boot application that implements a RESTful endpoint to calculate reward points earned by customers based on their purchases over a three-month period.
## Purpose

The purpose of this application is to showcase how to create and package a Java application into a JAR file and share it on GitHub.

## Prerequisites

Before running this application, ensure you have the following installed:

- Java Development Kit (JDK) 8 or higher
- Apache Maven

## Build and Run

To build and run the application, follow these steps:

1. Clone this repository to your local machine:
   ```sh
   git clone https://github.com/BowensCode21020/RewardPointsServiceApplication.git
2. Build and run the application:
   ```sh
   cd RewardPointsServiceApplication
   mvn spring-boot:run

3. Access the following endpoints ( I used Postman API for screenshots) :

`POST /transactions/add`: Add a new transaction.
![ValueAdded1.png](..%2F..%2F..%2FOneDrive%2FDesktop%2FValueAdded1.png)
`GET /transactions/calculate-points?customerId={customerId}`: Calculate reward points for a customer.
![CalculatePoints1.png](..%2F..%2F..%2FOneDrive%2FDesktop%2FCalculatePoints1.png)
`GET /transactions/all`: Retrieve all transactions.
![ValueAdded2.png](..%2F..%2F..%2FOneDrive%2FDesktop%2FValueAdded2.png)