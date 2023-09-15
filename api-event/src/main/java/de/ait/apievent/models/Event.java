package de.ait.apievent.models;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Event {
    private Long id;
    private String title;
    private String description;

}