package de.ait.bootapp.dto;

public class EventDto {
    private String title;
    private String description;

    public EventDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public EventDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
