CREATE TABLE event (
    eventId INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    eventName VARCHAR(50) NOT NULL,
    totalTickets INTEGER NOT NULL
);

CREATE TABLE ticket (
    ticket_id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    event_id INTEGER NOT NULL REFERENCES event(eventId),
    reserved BOOLEAN NOT NULL DEFAULT FALSE,
    email VARCHAR(255) NOT NULL,
    bookedTs DATETIME
);