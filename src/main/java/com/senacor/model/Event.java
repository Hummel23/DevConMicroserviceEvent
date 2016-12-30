package com.senacor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.LocalDateSerializer;
import org.bson.types.ObjectId;
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
public class Event extends ResourceSupport {

    @Id
    private String eventId;

    private String name;

    private String place;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;

    @JsonIgnore
    private List<Speech> speeches;

    @JsonIgnore
    private List<String> attendees;


    public Event() {
        this.speeches = new ArrayList<>();
        this.attendees = new ArrayList<>();
        this.eventId = ObjectId.get().toString();

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

    public List<Speech> getSpeeches() {
        return speeches;
    }

    public void setSpeeches(List<Speech> speeches) {
        this.speeches = speeches;
    }


    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public List<String> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<String> attendees) {
        this.attendees = attendees;
    }

    public List<Speech> insertSpeechSorted(Speech addedSpeech) {
        boolean wasAdded = false;
        /*if (this.speeches.isEmpty()) {
            System.out.println("first speech is added");
            System.out.println(addedSpeech.getStartTime());
            this.speeches.add(addedSpeech);
            wasAdded = true;
        }else {*/
        for (int i = 0; i < this.speeches.size(); i++) {
            System.out.println(i + ". round in loop");
            System.out.println(this.speeches.get(i).getStartTime().isAfter(addedSpeech.getStartTime()));
            if (this.speeches.get(i).getStartTime().isAfter(addedSpeech.getStartTime())) {
                this.speeches.add(i, addedSpeech);
                wasAdded = true;
                break;
            }
        }
        if (!wasAdded) {
            this.speeches.add(addedSpeech);
        }
//        }

        return this.speeches;
    }

}
