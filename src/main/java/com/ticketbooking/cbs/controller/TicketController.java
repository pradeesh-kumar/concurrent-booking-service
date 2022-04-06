/*
 * Copyright (c) 2022. Pradeesh Kumar
 */

package com.ticketbooking.cbs.controller;

import com.ticketbooking.cbs.model.ReservationRequest;
import com.ticketbooking.cbs.model.TicketResponse;
import com.ticketbooking.cbs.service.TicketService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller that accepts book ticket requests
 * <p>
 * The arguments are expected to be not null. If the reservation is successfully,
 * book ticket returns the ticket entity with http response status CREATED(201), otherwise returns CONFLICT(409)
 * </p>
 */
@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
@Tag(name = "Ticket Api")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<TicketResponse> bookTicket(@RequestBody ReservationRequest reservationRequest) {
        TicketResponse response = ticketService.book(reservationRequest);
        if (response.status() == TicketResponse.ReservationStatus.SUCCESS) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }
}
