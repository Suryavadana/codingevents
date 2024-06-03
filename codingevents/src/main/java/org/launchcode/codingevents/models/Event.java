package org.launchcode.codingevents.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;

@Entity
public class Event extends AbstractEntity {

//    @Id
//    @GeneratedValue
//    private int id;
    @NotBlank(message = "Name is required.")
    @Size (min = 3, max = 50 , message = "Name must be between 3 and 50 characters.")
    private String name;
    @Size(max = 500, message = "Description too long!")
    private String description;
    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid Email. Try again.")
    private String contactEmail;
    @NotBlank(message = "Location is required.")
    private String location;
    @AssertTrue(message = "Registration Required.")
    private boolean registrationRequired;

    @ManyToOne
    @NotNull(message = "Category is required")
    private EventCategory eventCategory;
    @Positive(message="Number of attendees must be one or more.")
    private int numberOfAttendees;
    public Event(String name, String description , String contactEmail ,String location, boolean registrationRequired, int numberOfAttendees, EventCategory eventCategory) {
        this.name = name;
        this.description=description;
        this.contactEmail=contactEmail;
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

    public String getDescription() {
        return description;
    }

    public String getContactEmail()
    {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public void setDescription(String description) {
        this.description = description;
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
