package de.ait.apievent.services.impl;

import de.ait.apievent.dto.NewEventDto;
import de.ait.apievent.models.Event;
import de.ait.apievent.repositories.EventsRepository;
import de.ait.apievent.services.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import static de.ait.apievent.dto.EventDto.from;

@RequiredArgsConstructor
@Service
public class EventsServiceImpl implements EventsService {
    private final EventsRepository eventsRepository;

    @Override
    public NewEventDto addEvent(NewEventDto newEvent) {
        NewEventDto event = new NewEventDto(newEvent.getTitle(),
                newEvent.getDescription());

        eventsRepository.save(event);

        return event;
    }

    @Override
    public List<Event> getAllEvents() {
       return eventsRepository.findAll();
    }
}
