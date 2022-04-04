package com.ticketbooking.cbs.service;

import com.ticketbooking.cbs.model.TicketResponse;

public interface TicketService {

    TicketResponse book(int eventId, String name, String location, int requiredTickets);
}
