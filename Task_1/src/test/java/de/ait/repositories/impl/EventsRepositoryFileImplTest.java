package de.ait.repositories.impl;

import de.ait.models.Event;
import org.junit.jupiter.api.*;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@DisplayName(" EventsRepositoryFileImpl is work ...")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class EventsRepositoryFileImplTest {
    private static final String TEMP_EVENTS_FILE_NAME = "events_test.txt";
    private EventsRepositoryFileImpl eventsRepository;

    @BeforeEach
    public void setUp() throws Exception {

       createNewFileForTest(TEMP_EVENTS_FILE_NAME);

       eventsRepository = new EventsRepositoryFileImpl(TEMP_EVENTS_FILE_NAME);
    }
    @AfterEach
    public void tearDown() throws Exception {
        deleteFileAfterTest(TEMP_EVENTS_FILE_NAME);
    }

    @DisplayName("save():")
    @Nested
    class Save {

        @Test
        public void writes_correct_line_to_file() throws Exception {
            Event event = new Event("Party",LocalDate.of(2023, 5, 10), LocalDate.of(2023, 5, 11));

            eventsRepository.save(event);

            String expected = "1|Party|2023-05-10|2023-05-11";

            BufferedReader reader = new BufferedReader(new FileReader(TEMP_EVENTS_FILE_NAME));

            String actual = reader.readLine();

            reader.close();

            assertEquals(expected, actual);
        }
    }
    @DisplayName("findAll():")
    @Nested
    class FindAll {

        @Test
        public void returns_correct_list_of_events() throws Exception {
            BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_EVENTS_FILE_NAME));

            writer.write("1|Concert|2023-12-24|2023-12-24");
            writer.newLine();
            writer.write("2|Party|2023-10-02|2023-10-04");
            writer.newLine();
            writer.close();
            List<Event> expected = Arrays.asList(
                    new Event(1L, "Concert", LocalDate.of(2023,12,24),LocalDate.of(2023,12,24)),
                    new Event(2L, "Party",LocalDate.of(2023,10, 2), LocalDate.of(2023,10, 4))
            );

            List<Event> actual = eventsRepository.findAll();
            assertEquals(expected, actual);
        }
    }


    private static void createNewFileForTest(String fileName) throws IOException {

        File file = new File(fileName);

        deleteIfExists(file);
        boolean result = file.createNewFile();

        if (!result) {
            throw new IllegalStateException("Problems with file create");
        }
    }

    private static void deleteIfExists(File file) {
        if (file.exists()) {
            boolean result = file.delete();
            if (!result) {
                throw new IllegalStateException("Problems with file delete");
            }
        }
    }

    private static void deleteFileAfterTest(String fileName) {
        File file = new File(fileName);

        deleteIfExists(file);
    }
}