package de.ait.ec.controllers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
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
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_empty_list_of_courses() throws Exception {
            mockMvc.perform(get("/api/courses"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()",is(0)));
        }

        @Test
        @Sql(scripts = {"/sql/data.sql"})
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_list_of_courses_for_empty_database() throws Exception {
            mockMvc.perform(get("/api/courses"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()", is(3)))
                    .andExpect(jsonPath("$.[0].id",is(1)))
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
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_created_course() throws Exception {
            mockMvc.perform(post("/api/courses")
                            .contentType("application/json")
                            .content("{\n" +
                                    "  \"title\": \"New Course\",\n" +
                                    "  \"description\": \"Description course\",\n" +
                                    "  \"beginDate\": \"2023-10-01\",\n" +
                                    "  \"endDate\": \"2024-01-10\",\n" +
                                    "  \"price\": 115.0\n" +
                                    "}"))
                            .andExpect(status().isCreated())
                            .andExpect(jsonPath("$.id",is(4)));
        }
        @Test
        public  void return_400_for_not_valid_course() throws Exception {
            mockMvc.perform(post("/api/courses")
                            .contentType("application/json")
                            .content("{\n" +
                                    "  \"title\": \"New Course\",\n" +
                                    "  \"description\": \"Description course\",\n" +
                                    "  \"beginDate\": \"2023-150-01\",\n" +
                                    "  \"endDate\": \"2024-01-10\",\n" +
                                    "  \"price\": 1150.0\n" +
                                    "}"))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errors.size()",is(2)));
        }

    }

    @Nested
    @DisplayName("GET /courses/{course-id}:")
    public class GetCourse{
        @Test
        @Sql(scripts = {"/sql/data.sql"})
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_existed_course() throws Exception {
            mockMvc.perform(get("/api/courses/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id",is(1)))
                    .andExpect(jsonPath("$.title",is("Course1Title")))
                    .andExpect((jsonPath("$.price",is(100.0))));
        }

        @Test
        @Sql(scripts = {"/sql/data.sql"})
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_404_for_existed_course() throws Exception {
            mockMvc.perform(get("/api/courses/5"))
                    .andExpect(status().isNotFound());

        }


    }
}