package com.ticketbooking.cbs;

import org.springframework.stereotype.Service;

@Service
public class DefaultTicketService implements TicketService {

    @Override
    public TicketResponse book(String name, String location) {
        return null;
    }
}
