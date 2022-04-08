/*
 * Copyright (c) 2022. Pradeesh Kumar
 */

package com.ticketbooking.cbs.service;

import com.ticketbooking.cbs.model.Event;
import com.ticketbooking.cbs.model.ReservationRequest;
import com.ticketbooking.cbs.model.Ticket;
import com.ticketbooking.cbs.model.TicketResponse;
import com.ticketbooking.cbs.repository.EventRepository;
import com.ticketbooking.cbs.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The service class performs the ticket {@link Ticket} related service logics.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultTicketService implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketLoaderHelper ticketLoaderHelper;
    private final EventRepository eventRepository;

    /**
     * Accepts the reservation request and reserves the ticket as per requested
     *
     * @param request reservation request
     * @return Ticket response
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, timeout = 2)
    public TicketResponse book(ReservationRequest request) {
        List<Ticket> tickets = getAvailableTickets(request.eventId(), request.ticketsRequired());
        return tickets.isEmpty() ? TicketResponse.unavailable() : reserve(tickets, request.email(), request.eventId());
    }

    /**
     * Reserves the ticket to the given email id
     *
     * @param tickets tickets to be reserved
     * @param email email id to which the ticket to be registered
     * @return reserved tickets
     */
    private TicketResponse reserve(List<Ticket> tickets, String email, int eventId) {
        tickets.forEach(ticket -> {
            ticket.setEmail(email);
            ticket.setReserved(true);
            ticket.setReservedTs(LocalDateTime.now());
        });
        ticketRepository.saveAll(tickets);
        log.info("{} Ticket has been reserved to {} for an event {}", tickets.size(), email, eventId);
        return TicketResponse.success(tickets);
    }

    /**
     * Returns the unreserved tickets
     *
     * @param requiredCount total tickets needed
     * @return unreserved tickets
     */
    private List<Ticket> getAvailableTickets(int eventId, int requiredCount) {
        List<Ticket> unreservedTickets = ticketRepository.findAllUnreserved(eventId, requiredCount);
        int moreTicketsNeeded = requiredCount - unreservedTickets.size();
        if (moreTicketsNeeded == 0) {
            return unreservedTickets;
        }
        List<Ticket> moreTickets = eventRepository.findById(eventId)
                .map(event -> getAdditionalTickets(event, moreTicketsNeeded))
                .orElse(List.of());
        if (moreTickets.isEmpty()) {
            return List.of();
        }
        List<Ticket> fulfilledTickets = new ArrayList<>();
        fulfilledTickets.addAll(unreservedTickets);
        fulfilledTickets.addAll(moreTickets);
        return fulfilledTickets;
    }

    /**
     * Provides the additionally required tickets
     *
     * @param event the event for which tickets have to be created
     * @param requiredTickets required number of tickets
     * @return created tickets
     */
    private List<Ticket> getAdditionalTickets(Event event, int requiredTickets) {
        int uncreatedTickets = event.getTotalTickets() - event.getCreatedTickets();
        if (uncreatedTickets < requiredTickets) {
            return List.of();
        }
        return ticketLoaderHelper.fullfillTickets(event, requiredTickets);
    }
}
