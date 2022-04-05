/*
 * Copyright (c) 2022. Pradeesh Kumar
 */

package com.ticketbooking.cbs.model;

import java.time.LocalDateTime;

/**
 * The entity class represents a ticket for an event
 * @see Event
 */
public record Ticket(int id, int eventId, boolean reserved, LocalDateTime bookedTs, String email) {}
