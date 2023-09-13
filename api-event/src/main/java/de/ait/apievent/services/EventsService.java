package de.ait.apievent.services;



import de.ait.apievent.dto.NewEventDto;
import de.ait.apievent.models.Event;

import java.util.List;

public interface EventsService {
    NewEventDto addEvent(NewEventDto newevent);
    List<Event> getAllEvents();
}
