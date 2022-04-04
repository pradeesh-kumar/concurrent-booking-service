package com.ticketbooking.cbs.service;

import com.ticketbooking.cbs.model.Event;
import com.ticketbooking.cbs.model.TicketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultTicketService implements TicketService {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public TicketResponse book(int eventId, String name, String location, int requiredTickets) {
        Event event = getEvent(eventId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid event id!"));
        if (event.availableTickets() < requiredTickets) {
            return TicketResponse.unavailable();
        }
        return reserve(event, name, location, requiredTickets);
    }

    private TicketResponse reserve(Event event, String name, String location, int requiredTickets) {
        return null; // TODO Implement
    }

    private Optional<Event> getEvent(int eventId) {
        return Optional.empty();
    }

}
