/*
 * Copyright (c) 2022. Pradeesh Kumar
 */

package com.ticketbooking.cbs.service;

import com.ticketbooking.cbs.model.CreateEventRequest;
import com.ticketbooking.cbs.model.Event;
import com.ticketbooking.cbs.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * The service class performs the ticket {@link Event} related service logics.
 */
@Service
@RequiredArgsConstructor
public class DefaultEventService implements EventService {

    private final EventRepository eventRepository;
    private final TicketLoaderHelper ticketLoaderHelper;

    @Override
    @Transactional
    public void create(CreateEventRequest request) {
        Event saved = eventRepository.save(request.event());
        int ticketsCreatedCount = ticketLoaderHelper.prefillTickets(saved, request.popular());
        saved.setCreatedTickets(ticketsCreatedCount);
        eventRepository.save(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Event> findById(int eventId) {
        return eventRepository.findById(eventId);
    }

}
