/*
 * Copyright (c) 2022. Pradeesh Kumar
 */

package com.ticketbooking.cbs.repository;

import com.ticketbooking.cbs.model.Ticket;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

/**
 * The crud repository for {@link Ticket} entity
 */
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    List<Ticket> findAllByEventIdAndReserved(int eventId, boolean reserved, Pageable pageable);

    default List<Ticket> findAllUnreserved(int eventId, int limit) {
        return findAllByEventIdAndReserved(eventId, false, PageRequest.ofSize(limit));
    }
}
