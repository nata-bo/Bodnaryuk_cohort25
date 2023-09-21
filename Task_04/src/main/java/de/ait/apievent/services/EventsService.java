package de.ait.apievent.services;



import de.ait.apievent.dto.EventDto;
import de.ait.apievent.dto.NewEventDto;
import de.ait.apievent.dto.UpdateEventDto;

import java.util.List;

public interface EventsService {
    EventDto addEvent(NewEventDto newEvent);
    List<EventDto> getAllEvents();

    EventDto getEvent(Long id);

    EventDto updateEvent(Long id, UpdateEventDto updateEvent);

    EventDto deleteEvent(Long id);
}



