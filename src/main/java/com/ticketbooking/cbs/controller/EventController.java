/*
 * Copyright (c) 2022. Pradeesh Kumar
 */
package com.ticketbooking.cbs.controller;

import com.ticketbooking.cbs.model.CreateEventRequest;
import com.ticketbooking.cbs.model.Event;
import com.ticketbooking.cbs.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller that accepts crud requests for the entity {@link Event}
 */
@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<Event> create(@RequestBody CreateEventRequest createEventRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.create(createEventRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> findById(int eventId) {
        return eventService.findById(eventId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
