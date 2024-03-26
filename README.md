# Reward Point Service Application

This is a Spring Boot application that implements a RESTful endpoint to calculate reward points earned by customers based on their purchases over a three-month period.
## Purpose

The purpose of this application is to showcase a service API / backend application to complement the UI front end and display proficiency in API / backend development.

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

3. Access the following endpoints ( I used Postman API for screenshots) :

`POST /transactions/add`: Add a new transaction.
![ValueAdded1](https://github.com/BowensCode21020/RewardPointsServiceApplication/assets/74689536/d8fe09d5-b152-46af-bce1-ba9fc51d8513)
`GET /transactions/calculate-points?customerId={customerId}`: Calculate reward points for a customer.
![CalculatePoints1](https://github.com/BowensCode21020/RewardPointsServiceApplication/assets/74689536/3fb0c326-10e2-4027-bd82-9d94dcc0d164)
`GET /transactions/all`: Retrieve all transactions.
![ValueAdded2](https://github.com/BowensCode21020/RewardPointsServiceApplication/assets/74689536/9952ddd0-66f8-404f-b021-c237c15eb8d9)
