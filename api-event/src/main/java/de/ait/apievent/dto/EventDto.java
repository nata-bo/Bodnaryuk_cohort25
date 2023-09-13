package de.ait.apievent.dto;

import de.ait.apievent.models.Event;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Event", description = "Информация о событии")
public class EventDto {
    @Schema(description = "Событие", example = "Concert")
    private String title;
    @Schema(description = "Описание", example = "Hans Zimmer")
    private String description;

    public static  EventDto from(Event event){
       return new EventDto(event.getTitle(), event.getDescription());
    }
    public static List<EventDto> from(List<Event> events){
        return events.stream()
                .map(EventDto::from)
                .collect(Collectors.toList());
    }
}

