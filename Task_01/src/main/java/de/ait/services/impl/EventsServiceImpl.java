package de.ait.services.impl;

import de.ait.models.Event;
import de.ait.repositories.EventsRepository;
import de.ait.services.EventsService;

import java.time.LocalDate;
import java.util.List;

public class EventsServiceImpl implements EventsService {
    private final EventsRepository eventsRepository;

    public EventsServiceImpl(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public Event addEvent(String title, LocalDate startDate, LocalDate endDate) {
        if (title == null || title.equals("") || title.equals(" ")) {
            throw new IllegalArgumentException("Название не может быть пустым");
        }

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Дата окончания  не может быть раньше даты начала");
        }


        Event event = new Event(title,startDate,endDate);

        eventsRepository.save(event);

        return event;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventsRepository.findAll();
    }
}
