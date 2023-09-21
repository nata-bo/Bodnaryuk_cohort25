package de.ait;

import de.ait.controllers.EventsController;
import de.ait.repositories.EventsRepository;
import de.ait.repositories.impl.EventsRepositoryFileImpl;
import de.ait.repositories.impl.EventsRepositoryListImpl;
import de.ait.services.EventsService;
import de.ait.services.impl.EventsServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EventsRepository eventsRepositoryList = new EventsRepositoryListImpl();
        EventsRepository eventsRepositoryFile = new EventsRepositoryFileImpl("events.txt");
        EventsService eventsService = new EventsServiceImpl(eventsRepositoryFile);
        EventsController eventsController = new EventsController(scanner, eventsService);

        boolean isRun = true;

        while (isRun) {
            String command = scanner.nextLine();

            switch (command) {
                case "/addEvent" ->
                        eventsController.addEvent();
                case "/events" ->
                        eventsController.getAllEvents();
                case "/exit" -> isRun = false;
            }
        }
    }
}