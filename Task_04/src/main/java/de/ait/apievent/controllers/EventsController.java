package de.ait.apievent.controllers;


import de.ait.apievent.dto.EventDto;
import de.ait.apievent.dto.NewEventDto;
import de.ait.apievent.dto.UpdateEventDto;
import de.ait.apievent.services.EventsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tags(value = @Tag(name = "Events"))
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events")

public class EventsController {

private final EventsService eventsService;

    @Operation(summary = "Добавление нового события", description = "Доступно всем пользователям")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventDto addEvent(@RequestBody NewEventDto newEvent) {
        return eventsService.addEvent(newEvent);

    }
    @Operation(summary = "Получение всех событий", description = "Доступно администратору системы")
    @GetMapping
    public List<EventDto> getEventsPage(){
       return eventsService.getAllEvents();
    }

    @Operation(summary = "Получение одного события", description = "Доступно всем пользователям")
    @GetMapping("/{event-id}")
    public EventDto getEvent(@Parameter(description ="идентификатор события", example = "2")
                                 @PathVariable("event-id") Long id){
        return eventsService.getEvent(id);
    }

    @Operation(summary = "Обновление события", description = "Доступно администратору системы")
    @PutMapping("/{event-id}")
    public  EventDto updateEvent(@Parameter(description ="идентификатор события", example = "3")
                                 @PathVariable("event-id") Long id,
                                  @RequestBody UpdateEventDto updateEvent){
      return eventsService.updateEvent(id, updateEvent);
    }

    @Operation(summary = "Удаление события", description = "Доступно администратору системы")
    @DeleteMapping("/{event-id}")
    public EventDto deleteEvent(@Parameter(description ="идентификатор события", example = "2")
                                    @PathVariable("event-id") Long id) {
        return eventsService.deleteEvent(id);

    }

}
