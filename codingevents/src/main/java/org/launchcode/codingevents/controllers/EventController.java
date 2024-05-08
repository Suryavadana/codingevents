package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents(Model model){
        model.addAttribute("title","All Events");
        // Add the list of events to the model
        model.addAttribute("events", EventData.getAll());
        // Return the name of the template to render
        return "events/index";// Assuming you have a template named 'index.html' in the 'events' directory
    }
    //lives at /events/create
//    @GetMapping("create")
//    public String renderCreateEventForm(){
//        return "events/create";
//    }


    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        return "events/create";
    }
    //lives at /events/create
    @PostMapping("create")
    public String createEvent(@RequestParam String eventName , @RequestParam String eventDescription){
        EventData.add(new Event(eventName, eventDescription));
        return "redirect:/events";
    }
    // lives at "/events/delete"
    @GetMapping("delete")
    public String displayDeleteForm(Model model){
        model.addAttribute("title","Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";

    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam (required = false) int[]eventIds){
        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }

        return "redirect:/events";
    }
}
