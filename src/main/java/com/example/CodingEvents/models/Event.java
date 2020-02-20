package com.example.CodingEvents.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "Name required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Size(max = 500, message = "Description too long")
    private String description;

    @NotBlank(message = "Email required")
    @Email(message = "Invalid email, try again")
    private String contactEmail;

    private EventType type;

    public Event (){}

    public Event(String name, String description, String email, EventType type) {
        this.name = name;
        this.description = description;
        this.contactEmail = email;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }



    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}