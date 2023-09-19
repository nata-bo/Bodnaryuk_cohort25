package de.ait.apievent.repositories.impl;

import de.ait.apievent.dto.NewEventDto;
import de.ait.apievent.models.Event;
import de.ait.apievent.repositories.EventsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class EventsRepositoryJdbcImpl implements EventsRepository {

    private final DataSource dataSource;

    @Override
    public List<Event> findAll() {
        return null;
    }

    @Override
    public void save(Event model) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(dataSource)
                .usingGeneratedKeyColumns("id");

        jdbcInsert.withTableName("event");

        Map<String,Object> parameters = new HashMap<>();
        parameters.put("title", model.getTitle());
        parameters.put("description", model.getDescription());
        long generatedId = jdbcInsert.executeAndReturnKey(parameters).longValue();
        model.setId(generatedId);
    }

    @Override
    public void update(Event model) {

    }
}
