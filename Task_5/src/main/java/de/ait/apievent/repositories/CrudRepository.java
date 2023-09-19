package de.ait.apievent.repositories;

import de.ait.apievent.dto.EventDto;
import de.ait.apievent.dto.NewEventDto;
import de.ait.apievent.models.Event;

import java.util.List;

public interface CrudRepository <T> {
    List<T> findAll();

    void save(T model);

    void update(T model);

    T findById(Long id);

    void deleteById(Long id);
}
