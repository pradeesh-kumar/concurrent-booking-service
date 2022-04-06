/*
 * Copyright (c) 2022. Pradeesh Kumar
 */

package com.ticketbooking.cbs.service;

import com.ticketbooking.cbs.model.CreateEventRequest;
import com.ticketbooking.cbs.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultEventService implements EventService {

    private static final String QUERY_INSERT = "INSERT INTO event(eventName, totalTickets) VALUES(?, ?)";
    private static final String QUERY_GET = "SELECT * FROM event WHERE eventId = ?";

    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void create(CreateEventRequest request) {
        Event event = request.event();
        jdbcTemplate.update(QUERY_INSERT, event.getEventName(), event.getTotalTickets());
        prefillTickets(event.getEventId(), request.popular());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Event> findById(int eventId) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(QUERY_GET, new BeanPropertyRowMapper<>(Event.class), eventId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private void prefillTickets(int eventId, boolean isPopular) {

    }
}
