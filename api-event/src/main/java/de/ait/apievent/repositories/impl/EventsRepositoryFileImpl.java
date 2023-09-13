package de.ait.apievent.repositories.impl;


import de.ait.apievent.dto.NewEventDto;
import de.ait.apievent.models.Event;
import de.ait.apievent.repositories.EventsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventsRepositoryFileImpl implements EventsRepository {
    private final String fileName;

    public EventsRepositoryFileImpl(@Value("C:/ait_tr/backend/api-event/events.txt") String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Event> findAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            return reader.lines()
                    .map(line -> line.split("\\|"))
                    .map(parsed -> new Event(
                            parsed[0],
                            parsed[1]
                    ))
                    .collect(Collectors.toList());

        } catch (IOException e)  {
            throw new IllegalStateException("Проблемы с чтением из файла: " + e.getMessage());
        }
    }

    @Override
    public void save(NewEventDto event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {

            writer.write(event.getTitle() + "|"+event.getDescription());
            writer.newLine();

        } catch (IOException e) {
            throw new IllegalStateException("Проблемы с записью в файл: " + e.getMessage());
        }

    }

    @Override
    public void update(Event model) {

    }
}
