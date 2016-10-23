package com.senacor.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by saba on 21.10.16.
 */
public class Event {

    @Id
    private Long id;

    private String place;

    private Date date;

    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {

        return place;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public Long getId() {

        return id;
    }


}
