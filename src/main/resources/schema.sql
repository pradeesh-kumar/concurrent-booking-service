CREATE TABLE event (
    event_id INTEGER NOT NULL PRIMARY KEY,
    event_name VARCHAR(50) NOT NULL,
    available_tickets BIGINT NOT NULL
);

CREATE TABLE ticket (
    ticket_id BIGINT NOT NULL PRIMARY KEY,
    user_name VARCHAR(25) NOT NULL,
    event_id INTEGER NOT NULL REFERENCES event(event_id)
);