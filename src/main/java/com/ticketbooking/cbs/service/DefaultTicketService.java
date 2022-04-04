package com.ticketbooking.cbs.service;

import com.ticketbooking.cbs.model.TicketResponse;
import org.springframework.stereotype.Service;

@Service
public class DefaultTicketService implements TicketService {

    @Override
    public TicketResponse book(String name, String location) {
        return null;
    }
}
