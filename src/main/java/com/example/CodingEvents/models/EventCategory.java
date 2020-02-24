package com.example.CodingEvents.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EventCategory extends AbstractEntity {


    @Size(min = 3, message = "Name must have at least 3 characters")
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "eventCategory")
    private final List<Event> eventList = new ArrayList<>();

    public EventCategory(String name) {
        this.name = name;
    }

    public EventCategory() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEventList() {
        return eventList;
    }
}
