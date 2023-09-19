package de.ait.bootapp.services;

import de.ait.bootapp.dto.EventDto;
import de.ait.bootapp.models.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventsService {
    Event addEvent(EventDto eventDto);
    List<Event> getAllEvents();
}
