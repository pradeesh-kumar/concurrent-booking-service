package com.ticketbooking.cbs.model;

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

    enum ReservationStatus {
        SUCCESS, UNAVAILABLE, ERROR
    }
}
