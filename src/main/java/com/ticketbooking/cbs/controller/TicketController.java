package com.ticketbooking.cbs.controller;

import com.ticketbooking.cbs.model.TicketResponse;
import com.ticketbooking.cbs.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<TicketResponse> bookTicket(int eventId, String name, String location, int requiredTickets) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.book(eventId, name, location, requiredTickets));
    }
}
