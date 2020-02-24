package com.example.CodingEvents.controllers;

import com.example.CodingEvents.data.EventCategoriesRepository;
import com.example.CodingEvents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {

    @Autowired
    private EventCategoriesRepository eventCategoriesRepository;

    @GetMapping
    public String displayAllCategories (Model model) {
        model.addAttribute("title", "Event Categories");
        model.addAttribute("eventCategories", eventCategoriesRepository.findAll());

        return "eventCategories/index";
    }

    @GetMapping("create")
    public String renderCreateEventCategoryForm (Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute(new EventCategory());

        return "eventCategories/create";
    }

    @PostMapping("create")
    public String createEventCategory (@ModelAttribute @Valid EventCategory newEventCategory, Errors errors) {
        if (errors.hasErrors()) {
            return "eventCategories/create";
        }
        eventCategoriesRepository.save(newEventCategory);
        return "redirect:";
    }

    @PostMapping()
    public String deleteEventCategory (@RequestParam(required = false) int[] eventCategoryIds) {
        if (eventCategoryIds != null) {
            for (int id: eventCategoryIds) {
                eventCategoriesRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

}
