# Concurrent booking service
An example of highly concurrent service that enables booking tickets without overbooking

## Features
1. Book tickets
2. Cancel the booked ticket

## How it works internally?
upcoming...

## How to run?
1. Go to the class ConcurrentBookingServiceApplication.java
2. Run as Java application
3. Run the curl command given below

`curl --location --request POST 'http://localhost:8088/ticket?name=Alice&location=somewhere'`

To access the H2 database, go to `http://localhost:8080/h2-console`
Set jdbc url to `jdbc:h2:mem:testdb` in the h2 console