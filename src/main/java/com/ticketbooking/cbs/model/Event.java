/*
 * Copyright (c) 2022. Pradeesh Kumar
 */

package com.ticketbooking.cbs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The entity class represents an event
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Event {

    private int eventId;
    private String eventName;
    private int totalTickets;
}
