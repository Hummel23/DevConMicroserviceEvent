package com.senacor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saba on 21.10.16.
 */
@Document
public class Event extends ResourceSupport implements Comparable<Event>{

    @Id
    private String eventId;

    private String name;

    private String place;

    //TODO dates as localdate
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    private LocalDate date;


    @JsonIgnore
    private List<Speech> speeches;


    public Event() {
        this.speeches = new ArrayList<>();
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {

        return place;
    }

    public String getName() {
        return name;
    }

/*   public String getDate() {
        return date;
    }


    public void setDate (String date) {
        this.date = date;
}*/
    public List<Speech> getSpeeches() {
        return speeches;
    }

    public void setSpeeches(List<Speech> speeches) {
        this.speeches = speeches;
    }

    //
    public String getEventId() {
      return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;

    }

    @Override
    public int compareTo(Event event) {
        return this.getDate().compareTo(event.getDate());
    }
}
