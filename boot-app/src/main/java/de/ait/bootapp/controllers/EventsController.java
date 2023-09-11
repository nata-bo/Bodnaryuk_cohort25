package de.ait.bootapp.controllers;

import de.ait.bootapp.models.Event;
import de.ait.bootapp.services.EventsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class EventsController {

private final EventsService eventsService;

    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @PostMapping("/add_event")
    public String addEvent(@RequestParam("title") String title,
                           @RequestParam("description") String description,
                           @RequestParam("startDate") LocalDate startDate,
                           @RequestParam("endDate") LocalDate endDate) {
        eventsService.addEvent(title,description,startDate,endDate);
        return "redirect:/success_add_event.html";
    }
    @GetMapping("/events")
    public  String getEventsPage(Model model){
        List<Event> events = eventsService.getAllEvents();
        model.addAttribute("eventsList",events);
        return "events_page";
    }

}
