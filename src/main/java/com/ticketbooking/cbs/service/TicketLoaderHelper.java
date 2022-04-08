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
     * Returns the requiredTickets number of new tickets also performs the ticket reload
     *
     * @param event event pertaining to the ticket
     * @param requiredTickets total additional tickets required
     * @return the requiredTickets number of unreserved tickets
     */
    public List<Ticket> fullfillTickets(Event event, int requiredTickets) {
        int createdTickets = event.getCreatedTickets();
        int batchSize = Math.min((createdTickets + requiredTickets) * 2, event.getTotalTickets());
        List<Ticket> freshlyLoaded = loadTickets(event, batchSize);
        return freshlyLoaded.stream().limit(requiredTickets).toList();
    }
}
