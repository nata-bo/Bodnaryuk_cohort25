package de.ait.bootapp.services;

import de.ait.bootapp.models.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventsService {
    Event addEvent(String title,String description, LocalDate startDate, LocalDate endDate);
    List<Event> getAllEvents();
}
