package com.senacor.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by saba on 21.10.16.
 */
public class Event {

    @Id
    private String id;

    private String place;

    private LocalDate date;

    private String name;

    public Event(){
        
    }
    public Event(String name){
        this.name=name;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setDate(LocalDate date) {
         this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {

        return place;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getId() {

        return id;
    }


}
