/*
 * Copyright (c) 2022. Pradeesh Kumar
 */

package com.ticketbooking.cbs.service;

import com.ticketbooking.cbs.model.CreateEventRequest;
import com.ticketbooking.cbs.model.Event;

import java.util.Optional;

public interface EventService {

    void create(CreateEventRequest createEventRequest);
    Optional<Event> findById(int eventId);
}
