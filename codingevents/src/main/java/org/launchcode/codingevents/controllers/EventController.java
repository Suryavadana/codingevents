package org.launchcode.codingevents.controllers;

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
    private static List<Event> events = new ArrayList<>();
 //   public String getEVents(Model model){
//        // Create an empty list to store event names
//        List<String> events = new ArrayList<>();
//
//        // Add some event names to the list
//        events.add("Code With Pride");
//        events.add("Strange Loop");
//        events.add("Apple WWDC");
//        events.add("SpringOne platform");
//
//        // Add the list of event names to the model
//        model.addAttribute("events", events);
    @GetMapping
    public String displayAllEvents(Model model){
        // Add the list of events to the model
        model.addAttribute("events",events);
        // Return the name of the template to render
        return "events/index";// Assuming you have a template named 'index.html' in the 'events' directory
    }
    //lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(){
        return "events/create";
    }
    //lives at /events/create
    @PostMapping("create")
    public String createEvent(@RequestParam String eventName , @RequestParam String eventDescription){
        events.add(new Event(eventName, eventDescription));
        return "redirect:/events";
    }
}
