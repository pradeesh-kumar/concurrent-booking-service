CREATE TABLE event (
    event_id INTEGER NOT NULL PRIMARY KEY,
    event_name VARCHAR(50) NOT NULL,
    total_tickets INTEGER NOT NULL
);

CREATE TABLE ticket (
    ticket_id INTEGER NOT NULL PRIMARY KEY,
    event_id INTEGER NOT NULL REFERENCES event(event_id),
    reserved BOOLEAN NOT NULL DEFAULT FALSE,
    email VARCHAR(255) NOT NULL,
    bookedTs DATETIME
);