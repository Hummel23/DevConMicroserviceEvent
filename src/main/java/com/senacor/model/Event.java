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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saba on 21.10.16.
 */
@Document
public class Event extends ResourceSupport implements Serializable {

    @Id
    private String eventId;
    private String name;
    private String place;
    private String streetAndNumber;
    private String postalCodeAndCity;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;

    @JsonIgnore
    private List<Speech> speeches;

    @JsonIgnore
    private List<NaturalPerson> attendees;


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

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public void setStreetAndNumber(String streetAndNumber) {
        this.streetAndNumber = streetAndNumber;
    }

    public String getPostalCodeAndCity() {
        return postalCodeAndCity;
    }

    public void setPostalCodeAndCity(String postalCodeAndCity) {
        this.postalCodeAndCity = postalCodeAndCity;
    }

    public List<NaturalPerson> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<NaturalPerson> attendees) {
        this.attendees = attendees;
    }

}
