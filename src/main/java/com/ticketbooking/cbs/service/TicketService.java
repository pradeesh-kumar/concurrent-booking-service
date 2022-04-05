/*
 * Copyright (c) 2022. Pradeesh Kumar
 */

package com.ticketbooking.cbs.service;

import com.ticketbooking.cbs.model.ReservationRequest;
import com.ticketbooking.cbs.model.TicketResponse;

public interface TicketService {

    TicketResponse book(ReservationRequest reservationRequest);
}
