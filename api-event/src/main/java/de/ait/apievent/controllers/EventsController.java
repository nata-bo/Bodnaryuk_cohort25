package de.ait.apievent.controllers;


import de.ait.apievent.dto.NewEventDto;
import de.ait.apievent.models.Event;
import de.ait.apievent.services.EventsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Tags(value = @Tag(name = "Events"))
@RequiredArgsConstructor
@Controller
public class EventsController {

private final EventsService eventsService;

    @Operation(summary = "Добавление нового события", description = "Доступно администратору системы")
    @PostMapping("/add_event")
    @ResponseBody
    public NewEventDto addEvent(@RequestBody NewEventDto newEvent) {
        return eventsService.addEvent(newEvent);

    }
    @Operation(summary = "Получение всех событый", description = "Доступно администратору системы")
    @GetMapping("/events")
    @ResponseBody
    public List<Event> getEventsPage(){
       return eventsService.getAllEvents();
    }

}
