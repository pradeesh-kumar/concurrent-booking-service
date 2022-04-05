/*
 * Copyright (c) 2022. Pradeesh Kumar
 */

package com.ticketbooking.cbs.service;

import com.ticketbooking.cbs.model.CreateEventRequest;
import com.ticketbooking.cbs.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultEventService implements EventService {
    @Override
    public Event create(CreateEventRequest createEventRequest) {
        return null;
    }

    @Override
    public Optional<Event> findById(int eventId) {
        return Optional.empty();
    }
}
