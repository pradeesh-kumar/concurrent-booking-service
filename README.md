# Concurrent booking service
A demo application that's highly concurrent service that enables booking tickets without overbooking issue.

## What does a ticket booking service do?
* The system has a list of events. Every event has total number of available tickets.
* User can book tickets for an event
* User will not be able to book if the request amount of tickets exceed the available tickets for the event

## What is overbooking?
* Overbooking happens when multiple transactions able to read the same event from the table and they update the available tickets column
* Example: 
  * Event: id=1, name=Music concert, available_tickets=4
  * User A wants to book 3 tickets
  * User B wants to book 2 tickets same time of User A
  * Both the requests executed in a transaction, both transaction reads the event and available tickets as 4.
  * Transaction 1 successfully books the 3 tickets, updates the available tickets to 1
  * Transaction 2 successfully books the 2 tickets, updates the available tickets to 2
* Problem: Although the available tickets are 4, our system violated this rule and booked total of 5 tickets, also updated the available count to 2, which enables another user to book 2 tickets and the total booked tickets for the event will be 7

## How to resolve overbooking issue?
* We can acquire read-write lock on the entire event and then reserve the tickets.
* Example: 
  * Event: id=1, name=Music concert, available_tickets=4
  * User A wants to book 3 tickets
  * User B wants to book 2 tickets same time of User A
  * User A acquires the transaction, locks the event 1. While the user B waiting for the lock acquired by user A to be released.
  * User A successfully books 3 tickets, updates the available tickets to 1.
  * User B obtains the lock, reads the available ticket as 1, fails to book since available tickets is only 1, which exceeds the requested tickets.
* That's great!! isn't it??? But not... Is our system concurrent??
* Suppose there is a super popular event for which 5000 tickets available.
* Hundreds of users wants to book the ticket same time.
* Since we lock the entire event row, only one person will be able to book the ticket at a time, whereas 99 users will face lag in the application, face the timeout issue, this leads to the bad User Experience

## How to resolve the overbooking issue along with concurrent access?

## Features
1. Book tickets
2. Cancel the booked ticket

## How it works internally?
upcoming...

## How to run?
1. Go to the class ConcurrentBookingServiceApplication.java
2. Run as Java application
3. Run the curl command given below

`curl --location --request POST 'http://localhost:8088/ticket/book?eventId=1&name=Alice&location=somewhere&requiredTickets=5'`

To access the H2 database, go to `http://localhost:8080/h2-console`
Set jdbc url to `jdbc:h2:mem:testdb` in the h2 console

## How to do performance test?
Apache Jmeter project file is provided in this repo -> 'Ticket booking test.jmx'. Load it in your Apache Jmeter and perform the test.
