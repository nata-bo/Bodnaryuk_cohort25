package de.ait.apievent.controllers;


import de.ait.apievent.dto.EventDto;
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

    @Operation(summary = "Добавление пользователя", description = "Доступно администратору системы")
    @PostMapping("/add_event")
    @ResponseBody
    public EventDto addEvent(@RequestBody EventDto newEvent) {
        return eventsService.addEvent(newEvent);

    }
    @Operation(summary = "Получение всех пользователей", description = "Доступно администратору системы")
    @GetMapping("/events")
    @ResponseBody
    public  List<EventDto> getEventsPage(){
       return eventsService.getAllEvents();
    }

}
