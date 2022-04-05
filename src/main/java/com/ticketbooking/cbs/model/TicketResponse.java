/*
 * Copyright (c) 2022. Pradeesh Kumar
 */

package com.ticketbooking.cbs.model;

/**
 * The represents the outcome of the ticket {@link Ticket} reservation for an event {@link Event}.
 */
public record TicketResponse(Ticket ticket, ReservationStatus status) {

    public static TicketResponse success(Ticket ticket) {
        return new TicketResponse(ticket, ReservationStatus.SUCCESS);
    }

    public static TicketResponse unavailable() {
        return new TicketResponse(null, ReservationStatus.UNAVAILABLE);
    }

    public static TicketResponse error() {
        return new TicketResponse(null, ReservationStatus.ERROR);
    }

    public enum ReservationStatus {
        SUCCESS, UNAVAILABLE, ERROR
    }
}
