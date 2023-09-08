package de.ait.bootapp.services.impl;


import de.ait.bootapp.models.Event;
import de.ait.bootapp.repositories.EventsRepository;
import de.ait.bootapp.services.EventsService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class EventsServiceImpl implements EventsService {
    private final EventsRepository eventsRepository;

    public EventsServiceImpl(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public Event addEvent(String title,String description, LocalDate startDate, LocalDate endDate) {
        if (title == null || title.equals("") || title.equals(" ")) {
            throw new IllegalArgumentException("Название не может быть пустым");
        }

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Дата окончания  не может быть раньше даты начала");
        }


        Event event = new Event(title,description,startDate,endDate);

        eventsRepository.save(event);

        return event;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventsRepository.findAll();
    }
}
