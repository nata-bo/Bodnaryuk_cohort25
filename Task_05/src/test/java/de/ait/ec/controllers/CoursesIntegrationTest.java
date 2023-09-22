package de.ait.ec.controllers;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Endpoint /courses is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")

class CoursesIntegrationTest {
    @Autowired
     private MockMvc mockMvc;

    @Nested
    @DisplayName("GET /courses:")
    public class GetCourses{
        @Test
        public void return_empty_list_of_courses() throws Exception {
            mockMvc.perform(get("/api/courses"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()",is(0)));
        }

        @Test
        @Sql(scripts = {"/sql/data.sql"})
        public void return_list_of_courses() throws Exception {
            mockMvc.perform(get("/api/courses"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()", is(3)))
                    .andExpect(jsonPath("$.[0].title", is("Course1Title")))
                    .andExpect(jsonPath("$.[1].description", is("Course2Desc")))
                    .andExpect(jsonPath("$.[1].beginDate", is("2023-10-11")))
                    .andExpect(jsonPath("$.[2].endDate", is("2024-10-12")))
                    .andExpect(jsonPath("$.[2].price", is(130.0)));
        }

    }


    @Nested
    @DisplayName("POST /courses:")
    public class PostCourses{
        @Test
        @Sql(scripts = {"/sql/data.sql"})
        public void return_created_course() throws Exception {
            mockMvc.perform(post("/api/courses")
                            .contentType("application/json")
                            .content("{\n" +
                                    "  \"title\": \"ItCourse\",\n" +
                                    "  \"description\": \"Backend developer\",\n" +
                                    "  \"beginDate\": \"2023-10-01\",\n" +
                                    "  \"endDate\": \"2024-01-10\",\n" +
                                    "  \"price\": 115.0\n" +
                                    "}"))
                            .andExpect(status().isCreated());
        }

    }
}