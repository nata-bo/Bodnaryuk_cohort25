package de.ait.apievent.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(name = "NewEvent", description = "Информация о событии")
    public class NewEventDto {
        @Schema(description = "Событие", example = "Concert")
        private String title;
        @Schema(description = "Описание", example = "Hans Zimmer")
        private String description;

}