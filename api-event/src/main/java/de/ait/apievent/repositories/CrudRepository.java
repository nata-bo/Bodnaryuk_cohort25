package de.ait.apievent.repositories;

import de.ait.apievent.dto.EventDto;
import de.ait.apievent.dto.NewEventDto;

import java.util.List;

public interface CrudRepository <T> {
    List<T> findAll();

    void save(NewEventDto model);

    void update(T model);
}
