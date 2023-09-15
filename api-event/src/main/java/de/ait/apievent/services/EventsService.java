package de.ait.apievent.services;



import de.ait.apievent.dto.EventDto;
import de.ait.apievent.dto.NewEventDto;

import java.util.List;

public interface EventsService {
    EventDto addEvent(NewEventDto newEvent);
    List<EventDto> getAllEvents();
}
