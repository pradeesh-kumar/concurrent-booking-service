/*
 * Copyright (c) 2022. Pradeesh Kumar
 */

package com.ticketbooking.cbs.service;

import com.ticketbooking.cbs.model.ReservationRequest;
import com.ticketbooking.cbs.model.TicketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultTicketService implements TicketService {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public TicketResponse book(ReservationRequest reservationRequest) {
        return null;
    }
}
