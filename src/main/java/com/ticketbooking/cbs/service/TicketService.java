/*
 * Copyright (c) 2022. Pradeesh Kumar
 */

package com.ticketbooking.cbs.service;

import com.ticketbooking.cbs.model.ReservationRequest;
import com.ticketbooking.cbs.model.Ticket;
import com.ticketbooking.cbs.model.TicketResponse;

/**
 * The service interface specifies the operations related to {@link Ticket}
 */
public interface TicketService {

    TicketResponse book(ReservationRequest reservationRequest);
}
