package de.ait.services.impl;

import de.ait.models.Event;
import de.ait.repositories.EventsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EventsServiceImplTest {
    private static final String EXIST_Event_Title = "Party";
    private static final String NOT_EXIST_Event_Title = " ";
    private static final LocalDate START_DATE = LocalDate.of(2023,11,11);
    private static final LocalDate END_DATE = LocalDate.of(2023,11,12);
    private static final Event EXIST_EVENT = new Event(EXIST_Event_Title,START_DATE,END_DATE);


    private EventsServiceImpl eventsService;

    private EventsRepository eventsRepository;

    @BeforeEach
    public void setUp() {

        eventsRepository = Mockito.mock(EventsRepository.class);

        this.eventsService = new EventsServiceImpl(eventsRepository);
    }

    @Nested
    @DisplayName("addEvent():")
    class AddEvent {
        @Test
        public void on_incorrect_title_throws_exception() {
            assertThrows(IllegalArgumentException.class, () -> eventsService.addEvent(null, START_DATE,END_DATE));
        }

        @Test
        public void on_incorrect_data_throws_exception() {
            assertThrows(IllegalArgumentException.class, () -> eventsService.addEvent(EXIST_Event_Title,END_DATE,START_DATE));
        }

        @Test
        public void on_existed_event_throws_exception() {
            assertThrows(IllegalArgumentException.class, () -> eventsService.addEvent(NOT_EXIST_Event_Title, START_DATE,END_DATE));
        }

        @Test
        public void returns_created_user() {
            Event actual = eventsService.addEvent(EXIST_Event_Title,START_DATE,END_DATE);

            verify(eventsRepository).save(EXIST_EVENT);

            assertNotNull(actual);
        }
    }

    @Nested
    @DisplayName("getAllEvents()")
    class GetAllEvents{
        @Test
        public void getAllEventsTest() {
            List<Event> expectedEvents = Arrays.asList(
                    new Event("Concert",LocalDate.of(2023, 6,23),LocalDate.of(2023, 6,25)),
                    new Event("Party",LocalDate.of(2023, 2,20),LocalDate.of(2023, 2,20))
            );
            when(eventsRepository.findAll()).thenReturn(expectedEvents);
            List<Event> actualEvents = eventsService.getAllEvents();
            assertEquals(expectedEvents, actualEvents);
        }
    }

}