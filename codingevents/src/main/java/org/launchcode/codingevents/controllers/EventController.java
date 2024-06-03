package org.launchcode.codingevents.controllers;

import jakarta.validation.Valid;
import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    //findAll , save , findById
    @GetMapping
    public String displayAllEvents(@RequestParam(required = false) Integer categoryId, Model model) {

        if(categoryId == null) {

            model.addAttribute("title", "All Events");
            // Add the list of events to the model
            model.addAttribute("events", eventRepository.findAll( ));
        }
        else{
            Optional<EventCategory> result= eventCategoryRepository.findById(categoryId);
            if(result.isEmpty()){
                model.addAttribute("title","Invalid Category ID: " +categoryId);
            }
            else{
                EventCategory category = result.get();
                model.addAttribute("title", "EVents in category: "+category.getName());
                model.addAttribute("events", category.getEvents());
            }
        }
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
        model.addAttribute(new Event( ));
        model.addAttribute("categories", eventCategoryRepository.findAll( ));
        return "events/create";
    }

    //lives at /events/create
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if (errors.hasErrors( )) {
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
        eventRepository.save(newEvent);
        return "redirect:/events";
    }

    // lives at "/events/delete"
    @GetMapping("delete")
    public String displayDeleteForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventRepository.findAll( ));
        return "events/delete";

    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {
        if (eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }

        return "redirect:/events";
    }
//
//    @GetMapping("edit/{eventId}")
//    public String displayEditForm(Model model, @PathVariable int eventId){
//        // Retrieve the event to edit based on the eventId parameter
//        Event eventToEdit = EventData.getById(eventId);
//
//        // Add the event object to the model to make it available in the view
//        model.addAttribute("event", eventToEdit);
//
//        // Create a title for the edit form, including the event name and ID
//        String title = "Edit Event " + eventToEdit.getName() + " (id=" + eventToEdit.getId() + ")";
//        // Add the title to the model to display it in the view
//        model.addAttribute("title", title);
//
//        // Return the name of the template to render, in this case, "events/edit"
//        return "events/edit";
//    }
//    @PostMapping("edit")
//    public String processEditForm(int eventId, String name, String description) {
//        // Retrieve the event to be edited based on the provided eventId
//        Event eventToEdit = EventData.getById(eventId);
//
//        // Update the name and description of the event with the values submitted in the form
//        eventToEdit.setName(name);
//        eventToEdit.setDescription(description);

    // Redirect the user to the "/events" endpoint after editing the event

       // return "redirect:/events";
   // }

}
