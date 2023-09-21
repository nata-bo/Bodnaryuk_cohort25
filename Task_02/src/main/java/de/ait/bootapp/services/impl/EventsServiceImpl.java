package de.ait.bootapp.services.impl;


import de.ait.bootapp.dto.EventDto;
import de.ait.bootapp.models.Event;
import de.ait.bootapp.repositories.EventsRepository;
import de.ait.bootapp.services.EventsService;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class EventsServiceImpl implements EventsService {
    private final EventsRepository eventsRepository;

    public EventsServiceImpl(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public Event addEvent(EventDto eventDto) {
        if (eventDto.getTitle() == null || eventDto.getTitle().equals("") || eventDto.getTitle().equals(" ")) {
            throw new IllegalArgumentException("Название не может быть пустым");
        }
        if (eventDto.getDescription() == null || eventDto.getDescription() .equals("") || eventDto.getDescription() .equals(" ")) {
            throw new IllegalArgumentException("Описание не может быть пустым");
        }

        Event event = new Event(eventDto.getTitle(),
                eventDto.getDescription());

        eventsRepository.save(event);

        return event;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventsRepository.findAll();
    }
}
