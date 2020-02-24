package com.example.CodingEvents.controllers;


import com.example.CodingEvents.data.EventCategoriesRepository;
import com.example.CodingEvents.data.EventRepository;
import com.example.CodingEvents.models.Event;
import com.example.CodingEvents.models.EventCategory;
import com.example.CodingEvents.models.EventType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired  //dependency injection
    private EventRepository eventRepository;

    @Autowired
    private EventCategoriesRepository eventCategoriesRepository;

//    private static List<Event> eventsList = new ArrayList<>();

    //all redirect: end up to this mapping
//    @GetMapping
//    public String displayAllEvents (Model model) {
//        model.addAttribute("title", "All events");
// //        model.addAttribute("events", eventsList);
//        model.addAttribute("events", EventData.getAllEvents());
//        return "events/index";
//    }

    @GetMapping
    public String displayAllEvents (@RequestParam(required = false) Integer categoryId, Model model) {

        if (categoryId == null) {
            model.addAttribute("title", "All events");
            model.addAttribute("events", eventRepository.findAll());
        } else {

            Optional<EventCategory> result = eventCategoriesRepository.findById(categoryId);
            if (result.isPresent()) {
                EventCategory category = result.get();
                model.addAttribute("title", "Cat: " + category.getName());
                model.addAttribute("events", category.getEventList());

            } else {
                model.addAttribute("title", "No Results");
            }
        }
            return "events/index";

    }

    //lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm (Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event()); //passing information about the object with default constructor,
                                        // name attributes in the form are parsing field names (used also for connecting validation annotation from the class)
    //    model.addAttribute("types", EventType.values()); // array of enum values for rendering in the form
        model.addAttribute("categories", eventCategoriesRepository.findAll());
        return "events/create";
    }

    // lives at /events/create  (its a post method which posts data from the form, (in form, the method is post))
//    @PostMapping("create")
//    public String createEvent(@RequestParam String eventName, //Request parameter argument name (eventName) matches name="eventName" attribute in the input tag
//                              @RequestParam String eventDescription) {
//  //        eventsList.add(new Event(eventName, eventDescription));
//        EventData.addEvent(new Event(eventName, eventDescription));
//        return "redirect:"; //redirect to the root path of this controller (nothing after ':')
//    }


    @PostMapping("create")
    public String createEvent(@ModelAttribute @Valid Event newEvent, Errors errors) {
        /*model binding with ModelAttribute annotation
         when ModelAttribute is used, spring creates new object when the handler is called
         name attributes in the form need to match the objects field names
        @Valid enforces validation annotation from the object field and error object gets values from them*/
        if (errors.hasErrors()) {
            return "events/create";
        }
//        EventData.addEvent(newEvent);
        eventRepository.save(newEvent);
        return "redirect:";
    }

//    @GetMapping("delete")
//    public String displayDeleteEventForm (Model model) {
//        model.addAttribute("title", "Delete Event");
////        model.addAttribute("events", EventData.getAllEvents());
//        model.addAttribute("events", eventRepository.findAll());
//        return "events/delete";
//    }

    @PostMapping()
    public String deleteEvent(@RequestParam(required = false) int[] eventIds) {
        if (eventIds != null) {
            for (int id : eventIds) {
//                EventData.removeEvent(id);
                eventRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

    @GetMapping("details")
    public String displayEventDetails(@RequestParam Integer eventId, Model model) {
        Optional<Event> result = eventRepository.findById(eventId);

        if (result.isPresent()) {
            Event event = result.get();
            model.addAttribute("title", event.getName() + " Details");
            model.addAttribute("event", event);
        } else {
            model.addAttribute("title", "Invalid Event ID " + eventId);
        }
        return "events/details";
    }


}
