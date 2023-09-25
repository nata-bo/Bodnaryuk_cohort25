package de.ait.ec.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Schema(name = "NewCourse")
public class NewCourseDto {

     @Schema(description = " Course name", example = "Java")
     @NotNull
     @NotBlank
     @NotEmpty
     private String title;

     @Schema(description = " Course description", example = "Java development basis course")
     @Size(min = 5, max = 1000)
     @NotBlank
     @NotNull
     private String description;

     @Schema(description = " Course start date", example = "2022-02-02")
     @Pattern(regexp = "^(?:(?:19|20)\\d\\d)-(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2\\d|3[0-1])$")
     private String beginDate;

     @Schema(description = " Course end date", example = "2023-02-02")
     @Pattern(regexp = "^(?:(?:19|20)\\d\\d)-(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2\\d|3[0-1])$")
     private String endDate;

     @Schema(description = " Course cost", example = "150.0")
     @Min(value = 0)
     @Max(value = 200)
     private Double price;

}
