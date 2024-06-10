package org.launchcode.codingevents.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Event extends AbstractEntity {

    @NotBlank(message = "Name is required.")
    @Size (min = 3, max = 50 , message = "Name must be between 3 and 50 characters.")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private EventDetails eventDetails;
    @NotBlank(message = "Location is required.")
    private String location;
    @AssertTrue(message = "Registration Required.")
    private boolean registrationRequired;

    @ManyToOne
    @NotNull(message = "Category is required")
    private EventCategory eventCategory;
    @Positive(message="Number of attendees must be one or more.")
    private int numberOfAttendees;

    @ManyToMany
    private final List<Tag> tags = new ArrayList<>( );
    public Event(String name, String location, boolean registrationRequired, int numberOfAttendees, EventCategory eventCategory) {
        this.name = name;
        this.location=location;
        this.registrationRequired=registrationRequired;
        this.eventCategory=eventCategory;
        this.numberOfAttendees=numberOfAttendees;

    }
    public Event(){
        super( );
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isRegistrationRequired() {
        return registrationRequired;
    }

    public void setRegistrationRequired(boolean registrationRequired) {
        this.registrationRequired = registrationRequired;
    }

    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    //    public int getId() {
//        return id;
//    }


    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag){
        this.tags.add(tag);
    }
    @Override
    public String toString() {
        return name;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass( ) != o.getClass( )) return false;
//        Event event = (Event) o;
//        return id == event.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
