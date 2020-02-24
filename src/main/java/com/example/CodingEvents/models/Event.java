package com.example.CodingEvents.models;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Event extends AbstractEntity {


    @NotBlank(message = "Name required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;


    @ManyToOne  //many events can be related to one category
    @NotNull(message = "Category is required")
    private EventCategory eventCategory;

    @OneToOne(cascade = CascadeType.ALL) //tells hibernate to cascade down all operations performed on the event to the eventDetails (save,file,update)
    @Valid
    @NotNull
    private EventDetails eventDetails;

    public Event (){}

    public Event(String name, EventCategory eventCategory) {
        this.name = name;
        this.eventCategory = eventCategory;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }

    @Override
    public String toString() {
        return this.name;
    }


}
