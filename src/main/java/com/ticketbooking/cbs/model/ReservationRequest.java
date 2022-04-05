/*
 * Copyright (c) 2022. Pradeesh Kumar
 */

package com.ticketbooking.cbs.model;

/**
 * The pojo class represents client's request to reserve the ticket for an event {@link Event}
 * @see Ticket
 */
public record ReservationRequest(int eventId, String email, int ticketsRequired) {}
