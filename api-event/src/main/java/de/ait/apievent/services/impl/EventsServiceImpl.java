package de.ait.apievent.services.impl;

import de.ait.apievent.dto.EventDto;
import de.ait.apievent.models.Event;
import de.ait.apievent.repositories.EventsRepository;
import de.ait.apievent.services.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import static de.ait.apievent.dto.EventDto.from;

@RequiredArgsConstructor
@Service
public class EventsServiceImpl implements EventsService {
    private final EventsRepository eventsRepository;

    @Override
    public EventDto addEvent(EventDto newEvent) {
        Event event = new Event(newEvent.getTitle(),
                newEvent.getDescription());

        eventsRepository.save(event);

        return from(event);
    }

    @Override
    public List<EventDto> getAllEvents() {
       return from(eventsRepository.findAll());
    }
}
