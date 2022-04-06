/*
 * Copyright (c) 2022. Pradeesh Kumar
 */

package com.ticketbooking.cbs.model;

/**
 * The pojo class represents client's request to create a new {@link Event}
 */
public record CreateEventRequest(Event event, boolean popular) {}
