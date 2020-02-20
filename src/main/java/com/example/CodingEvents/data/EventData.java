package com.example.CodingEvents.data;


import com.example.CodingEvents.models.Event;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EventData {

    // need a place to put events
    private static final Map<Integer, Event> eventMap = new HashMap<>();

    // get all events
    public static Collection<Event> getAllEvents () {
        return eventMap.values();
    }

    // get a single vent
    public static Event getEventById (int id) {
        return eventMap.get(id);
    }

    // add an event
    public static void addEvent (Event event) {
        eventMap.put(event.getId(), event);
    }

    // remove an event
    public static void removeEvent (int id) {
        eventMap.remove(id);
    }

}
