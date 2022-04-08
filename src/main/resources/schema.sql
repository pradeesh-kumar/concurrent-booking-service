CREATE TABLE `event` (
    event_id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    event_name VARCHAR(50) NOT NULL,
    total_tickets INTEGER NOT NULL,
    created_tickets INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE ticket (
    ticket_id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    event_id INTEGER NOT NULL REFERENCES event(event_id),
    reserved BOOLEAN NOT NULL DEFAULT FALSE,
    email VARCHAR(255),
    reserved_ts DATETIME
);