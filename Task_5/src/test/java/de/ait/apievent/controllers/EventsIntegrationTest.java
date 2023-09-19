package de.ait.apievent.controllers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Endpoint /events is works:")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EventsIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("GET /events")
    public class GetUsers {
        @Test
        @Sql(scripts = {"/sql/schema.sql", "/sql/data.sql"})
        public void return_list_of_events() throws Exception {
            mockMvc.perform(get("/api/events")).andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()", is(4)))
                    .andExpect(jsonPath("$.[0].id", is(1)))
                    .andExpect(jsonPath("$.[0].title", is("Event1Title")))
                    .andExpect(jsonPath("$.[0].description", is("Event1Description")))
                    .andExpect(jsonPath("$.[1].id", is(2)))
                    .andExpect(jsonPath("$.[1].title", is("Event2Title")))
                    .andExpect(jsonPath("$.[1].description", is("Event2Description")))
                    .andExpect(jsonPath("$.[2].id", is(3)))
                    .andExpect(jsonPath("$.[2].title", is("Event3Title")))
                    .andExpect(jsonPath("$.[2].description", is("Event3Description")));

        }
    }
    @Nested
    @DisplayName("POST /events")
    public class PostEvents {
        @Test
        @Sql(scripts = {"/sql/schema.sql", "/sql/data.sql"})
        public void  return_created_event() throws Exception {
            mockMvc.perform(post("/api/events")
                    .contentType("application/json")
                    .content("{\n" +
                            "  \"title\": \"Congress\",\n" +
                            "  \"description\": \"Prague 14.06.2024\"\n" +
                            "}"))
                     .andExpect(jsonPath("$.id", is(5)))
                     .andExpect(status().isCreated());
        }

    }
}