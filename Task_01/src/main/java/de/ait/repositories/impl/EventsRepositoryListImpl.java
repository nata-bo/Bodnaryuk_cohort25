package de.ait.repositories.impl;

import de.ait.models.Event;
import de.ait.repositories.EventsRepository;

import java.util.ArrayList;
import java.util.List;

public class EventsRepositoryListImpl implements EventsRepository {
    private final List<Event> events = new ArrayList<>();
    private Long generatedId = 1L;


    @Override
    public List<Event> findAll() {
        return new ArrayList<>(events);
    }

    @Override
    public void save(Event event) {
      events.add(event);
      event.setId(generatedId);
      generatedId++;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(Event model) {

    }
}