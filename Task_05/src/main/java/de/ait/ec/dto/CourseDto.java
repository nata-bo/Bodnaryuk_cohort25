package de.ait.ec.dto;

import de.ait.ec.models.Course;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Course", description = "Description course")
public class CourseDto {
    @Schema(description = " Course ID", example = "1")
    private Long id;
    @Schema(description = " Course name", example = "Java")
    private String title;
    @Schema(description = " Course description", example = "Java development basis course")
    private String description;
    @Schema(description = " Course start date", example = "2022-02-02")
    private String beginDate;
    @Schema(description = " Course end date", example = "2023-02-02")
    private String endDate;
    @Schema(description = " Course cost", example = "150.0")
    private Double price;
    @Schema(description = " Course status - DRAFT, PUBLISHED", example = "DRAFT")
    private String state;

    public static CourseDto from(Course course){
        return CourseDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .beginDate(course.getBeginDate().toString())
                .endDate(course.getEndDate().toString())
                .price(course.getPrice())
                .state(course.getState().toString())
                .build();
    }
    public static List<CourseDto> from(List<Course> courses){
        return courses
                .stream()
                .map(CourseDto::from)
                .collect(Collectors.toList());
    }
}
