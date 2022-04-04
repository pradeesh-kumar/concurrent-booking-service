package com.ticketbooking.cbs.service;

import com.ticketbooking.cbs.model.TicketResponse;

public interface TicketService {

    TicketResponse book(String name, String location);
}
