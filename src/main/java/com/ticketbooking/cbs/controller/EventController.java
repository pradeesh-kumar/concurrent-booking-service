/*
 * Copyright (c) 2022. Pradeesh Kumar
 */
package com.ticketbooking.cbs.controller;

import com.ticketbooking.cbs.model.CreateEventRequest;
import com.ticketbooking.cbs.model.Event;
import com.ticketbooking.cbs.service.EventService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller that accepts crud requests for the entity {@link Event}
 */
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Tag(name = "Event Api")
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<Event> create(@RequestBody CreateEventRequest createEventRequest) {
        eventService.create(createEventRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> findById(@PathVariable int eventId) {
        return eventService.findById(eventId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
