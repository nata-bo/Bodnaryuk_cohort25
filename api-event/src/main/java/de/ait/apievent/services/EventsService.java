package de.ait.apievent.services;



import de.ait.apievent.dto.EventDto;

import java.util.List;

public interface EventsService {
    EventDto addEvent(EventDto eventDto);
    List<EventDto> getAllEvents();
}
