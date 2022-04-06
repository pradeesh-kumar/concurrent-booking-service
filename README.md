# Concurrent booking service
A highly concurrent demo application that enables booking tickets without overbooking issues.

## What does a ticket booking service do?
* The events can be created with a total number of available tickets.
* User can book tickets for an event.
* User will not be able to book if the requested amount of tickets exceeds the available tickets for the event.

## What is overbooking?
* Overbooking happens when multiple transactions are able to read the same event from the table and update the available tickets column.
* Example: 
  * Event: id=1, name=Music concert, available_tickets=4
  * User A wants to book 3 tickets
  * User B wants to book 2 tickets same time as User A
  * Both the requests executed in a transaction, both transactions read the event and available tickets as 4.
  * Transaction 1 successfully books the 3 tickets, updates the available tickets to 1
  * Transaction 2 successfully books the 2 tickets, updates the available tickets to 2
Problem: Although the available tickets are 4, our system violated this rule and booked a total of 5 tickets. Also updated the available count to 2, this allows 2 more tickets to be booked. The total booked tickets for the event will be 7.

## How to resolve the overbooking issue?
* We can acquire a read-write lock on the entire event row and then reserve the tickets.
* Example:
  * Event: id=1, name=Music concert, available_tickets=4
  * User A wants to book 3 tickets
  * User B wants to book 2 tickets same time as User A
  * User A acquires the transaction and locks the event 1. While user B waiting for the lock acquired by user A to be released.
  * User A successfully books 3 tickets, and updates the available tickets to 1.
  * User B obtains the lock, reads the available ticket as 1, and fails to book since the available tickets are only 1, which exceeds the requested tickets.
* That's great!! isn't it??? But not... Is our system highly concurrent??
* Suppose there is a super popular event for which 5000 tickets are available.
* Hundreds of users want to book the ticket same time.
* Since we lock the entire event row, only one person will be able to book the ticket at a time, whereas 99 users will face lag in the application, face the timeout issue, which leads to the bad User Experience

## How to resolve the overbooking issue along with concurrent access?
* Approach 1: Create all the tickets and mark 'unreserved' in advance.
  Pros: With this approach, we can achieve the highest level of concurrency.
  Cons: This approach is good if most of the tickets are booked. Otherwise, there will be storage waste if the tickets are not booked.
        We might need to clean up the unreserved tickets after the event ended either manually or using a job scheduler.

* Approach 2: Initially create a specific number of 'unreserved' tickets, then double it based on some load factor, say '0.75f'.
  Pros: With this approach, we can achieve a good level of concurrency. There will be no space issue.
  Cons: If the event is most popular, then users might face latency during ticket reloading time.

## Which is the good approach?
Well! we can choose a hybrid approach. During the creation of the event, there must be an option to select if the event is going to be the most popular or not.
If it's going to be a popular one, we can follow approach 1. Otherwise, we should follow the 2nd approach.

## Features
1. Book tickets
2. Cancel the booked ticket

## How to run?
1. Go to the class ConcurrentBookingServiceApplication.java
2. Run as Java application
3. Swagger UI available at: http://localhost:8088/swagger-ui/index.html

To access the H2 database, go to `http://localhost:8080/h2-console`
Set jdbc url to `jdbc:h2:mem:testdb` in the h2 console

## How to do performance test?
Apache Jmeter project file is provided in this repo -> 'Ticket booking test.jmx'. Load it in your Apache Jmeter and perform the test.

# Contributing
If you want to contribute to this repository, please fork and raise PR. Any bug fixes, enhancements, typo corrections are welcome.
