/*
 * Copyright (c) 2022. Pradeesh Kumar
 */

package com.ticketbooking.cbs.service;

import com.ticketbooking.cbs.model.Event;
import com.ticketbooking.cbs.model.Ticket;
import com.ticketbooking.cbs.repository.EventRepository;
import com.ticketbooking.cbs.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Functionality to load the initial batch of tickets, reload the ticket when load factor threshold reached.
 */
@Component
@RequiredArgsConstructor
public class TicketLoaderHelper {

    private static final int TICKET_LOAD_BATCH_SIZE = 50;
    private static final float LOAD_FACTOR = 0.75f;

    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;

    /**
     * Performs the initial load of ticket for the specified event
     *
     * @param event     event for which tickets have to be created
     * @param isPopular whether the event is popular
     * @return the number of tickets created
     */
    public int prefillTickets(Event event, boolean isPopular) {
        int ticketsToCreate = isPopular ? event.getTotalTickets() : Math.min(event.getTotalTickets(), TICKET_LOAD_BATCH_SIZE);
        loadTickets(event, ticketsToCreate);
        return ticketsToCreate;
    }

    /**
     * Creates the specified number of unreserved tickets for the specified event
     *
     * @param event     the event for which tickets have to be created
     * @param batchSize the number of tickets to be created
     * @return created tickets
     */
    public List<Ticket> loadTickets(Event event, int batchSize) {
        List<Ticket> tickets = IntStream
                .range(0, batchSize)
                .mapToObj(i -> Ticket.create(event.getEventId())).toList();
        event.setCreatedTickets(event.getCreatedTickets() + batchSize);
        eventRepository.save(event);
        return ticketRepository.saveAll(tickets);
    }

    /**
     * Creates unreserved tickets if the LOAD_FACTOR threshold crossed
     *
     * @param eventId for which tickets have to be created
     * @return number of new tickets created
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public int reloadTickets(int eventId) {
        Event event = eventRepository.findById(eventId).get();
        float reservedCount = ticketRepository.countByEventIdAndReserved(event.getEventId(), true);
        float unreservedCount = event.getCreatedTickets() - reservedCount;
        float load = reservedCount / unreservedCount;
        if (load < LOAD_FACTOR) {
            return 0;
        }
        int batchSize = Math.min(event.getCreatedTickets() * 2, event.getTotalTickets() - event.getCreatedTickets());
        loadTickets(event, batchSize);
        return batchSize;
    }
}
