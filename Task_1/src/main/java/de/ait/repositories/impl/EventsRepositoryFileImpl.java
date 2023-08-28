package de.ait.repositories.impl;

import de.ait.models.Event;
import de.ait.repositories.EventsRepository;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class EventsRepositoryFileImpl implements EventsRepository {
    private final String fileName;

    private Long generatedId = 1L;

    public EventsRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Event> findAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            return reader.lines()
                    .map(line -> line.split("\\|"))
                    .map(parsed -> new Event(
                            Long.parseLong(parsed[0]),
                            parsed[1],
                            LocalDate.parse(parsed[2]),
                            LocalDate.parse(parsed[3])
                    ))
                    .collect(Collectors.toList());

        } catch (IOException e)  {
            throw new IllegalStateException("Проблемы с чтением из файла: " + e.getMessage());
        }
    }

    @Override
    public void save(Event event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {

            event.setId(generatedId);

            writer.write(event.getId() + "|" + event.getTitle() + "|" + event.getStartDate()+ "|" + event.getEndDate());
            writer.newLine();

            generatedId++;

        } catch (IOException e) {
            throw new IllegalStateException("Проблемы с записью в файл: " + e.getMessage());
        }



    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(Event model) {

    }
}
