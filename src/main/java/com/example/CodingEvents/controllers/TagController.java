package com.example.CodingEvents.controllers;

import com.example.CodingEvents.data.TagReporsitory;
import com.example.CodingEvents.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("tags")
public class TagController {

    @Autowired
    TagReporsitory tagReporsitory;

    @GetMapping
    public String displayTags(Model model) {
        model.addAttribute("title", "All Tags");
        model.addAttribute("tags", tagReporsitory.findAll());
        return "tags/index";
    }
    @GetMapping("create")
    public String displayCreateTagForm (Model model) {
        model.addAttribute("title", "Create tag");
        model.addAttribute(new Tag());
        return "tags/create";
    }

    @PostMapping("create")
    public String processCreateTagForm (@ModelAttribute @Valid Tag tag, Errors errors, Model model) {
        if (errors.hasErrors()){
            return "tags/create";
        }
        tagReporsitory.save(tag);
        return "redirect:";
    }

    @PostMapping
    public String deleteTag (@RequestParam(required = false) int[] tagsIds) {
        if (tagsIds != null) {
            for (int id: tagsIds) {
                tagReporsitory.deleteById(id);
            }
        }
        return "redirect:";
    }

}
