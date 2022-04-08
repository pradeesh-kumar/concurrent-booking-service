/*
 * Copyright (c) 2022. Pradeesh Kumar
 */

package com.ticketbooking.cbs.repository;

import com.ticketbooking.cbs.model.Ticket;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The crud repository for {@link Ticket} entity
 */
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    int countByEventIdAndReserved(int eventId, boolean reserved);

    List<Ticket> findAllByEventIdAndReserved(int eventId, boolean reserved, Pageable pageable);

    default List<Ticket> findAllUnreserved(int eventId, int limit) {
        return findAllByEventIdAndReserved(eventId, false, PageRequest.ofSize(limit));
    }
}
