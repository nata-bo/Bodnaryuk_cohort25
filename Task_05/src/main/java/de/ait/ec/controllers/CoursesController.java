package de.ait.ec.controllers;

import de.ait.ec.dto.CourseDto;
import de.ait.ec.dto.NewCourseDto;
import de.ait.ec.dto.StandardResponseDto;
import de.ait.ec.services.CoursesService;
import de.ait.ec.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/courses")
@Tags(value = @Tag(name = "Courses"))
public class CoursesController {

    private final CoursesService coursesService;

    @Operation(summary = "Adding a course", description = "Available to manager of education center")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "The course was created successfully",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = CourseDto.class))),
    @ApiResponse(responseCode = "400",
            description = "Validation error",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ValidationErrorsDto.class)))
    })
    @PostMapping
    public ResponseEntity<CourseDto>  addCourse(@RequestBody @Valid NewCourseDto newCourse){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body( coursesService.addCourse(newCourse));

    }


    @Operation(summary = "Getting all courses", description = "Available to all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Request processed successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CourseDto.class))
            ),
            @ApiResponse(responseCode = "404",
                    description = "Courses not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<List<CourseDto>> getCourses(){
        return ResponseEntity
        .ok(coursesService.getCourses());
    }

    @Operation(summary = "Getting one course", description = "Available to all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Request processed successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CourseDto.class))
            ),
            @ApiResponse(responseCode = "404",
                    description = "Course not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })
    @GetMapping("/{course-id}")
    public ResponseEntity<CourseDto>getCourse(@Parameter(description = "Course ID", example = "1")
                                                  @PathVariable("course-id") Long courseId){
        return ResponseEntity
                .ok(coursesService.getCourse(courseId));
    }
}
