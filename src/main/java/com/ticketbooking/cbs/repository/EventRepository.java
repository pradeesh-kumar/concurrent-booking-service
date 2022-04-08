/*
 * Copyright (c) 2022. Pradeesh Kumar
 */

package com.ticketbooking.cbs.repository;

import com.ticketbooking.cbs.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The crud repository for {@link Event} entity
 */
public interface EventRepository extends JpaRepository<Event, Integer> {
}
