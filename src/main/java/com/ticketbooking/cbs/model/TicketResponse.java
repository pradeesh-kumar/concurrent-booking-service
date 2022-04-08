/*
 * Copyright (c) 2022. Pradeesh Kumar
 */

package com.ticketbooking.cbs.model;

import java.util.List;

/**
 * The represents the outcome of the ticket {@link Ticket} reservation for an event {@link Event}.
 */
public record TicketResponse(List<Ticket> ticket, ReservationStatus status) {

    public static TicketResponse success(List<Ticket> ticket) {
        return new TicketResponse(ticket, ReservationStatus.SUCCESS);
    }

    public static TicketResponse unavailable() {
        return new TicketResponse(null, ReservationStatus.UNAVAILABLE);
    }

    public enum ReservationStatus {
        SUCCESS, UNAVAILABLE
    }
}
