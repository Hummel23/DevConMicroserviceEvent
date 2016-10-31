package com.senacor.model;

import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by saba on 21.10.16.
 */
public class Event {

    @Id
    private String id;

    private String name;

    private String place;

    private LocalDate date;



    public Event(){
        
    }

    //Date myDate = new Date();

    public Event(String name){
        this.name=name;
    }
    public void setId(String id) {
        this.id = id;
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

    public String getId() {

        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
