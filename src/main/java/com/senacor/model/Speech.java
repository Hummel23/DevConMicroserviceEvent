package com.senacor.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalTime;
import java.util.List;


@Document
public class Speech extends ResourceSupport{


    @Id
    private String speechId;
    private String speechTitle;
    private LocalTime startTime;
    private LocalTime endTime;
    private String speechRoom;
    private String speaker;
    private String speakerInfo;
    private String speechSummary;
    private String eventID;


/*
    Objekte werden dann von Werten wie folgt gebildet:LocalDate.of(2012, Month.DECEMBER, 12); // from values
     */

    public Speech(String eventID) {
        this.eventID=eventID;
        this.speechId = ObjectId.get().toString();
    }

    public Speech() {
        this.speechId = ObjectId.get().toString();
    }

    public void setSpeechId(String speechId) {
        this.speechId = speechId;
    }

    public String getSpeechTitle() {
        return speechTitle;
    }

    public void setSpeechTitle(String speechTitle) {
        this.speechTitle = speechTitle;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getSpeechRoom() {
        return speechRoom;
    }

    public void setSpeechRoom(String speechRoom) {
        this.speechRoom = speechRoom;

    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getSpeakerInfo() {
        return speakerInfo;
    }

    public void setSpeakerInfo(String speakerInfo) {
        this.speakerInfo = speakerInfo;
    }

    public String getSpeechSummary() {
        return speechSummary;
    }

    public void setSpeechSummary(String speechSummary) {
        this.speechSummary = speechSummary;
    }

     public String getSpeechId() {
        return speechId;
     }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public List<Speech> insertSpeechSorted(List<Speech> speeches) {
        boolean wasAdded = false;
        for (int i = 0; i < speeches.size(); i++) {
            System.out.println(i + ". round in loop");
            System.out.println(speeches.get(i).getStartTime().isAfter(this.getStartTime()));
            if (speeches.get(i).getStartTime().isAfter(this.getStartTime())) {
                speeches.add(i, this);
                wasAdded = true;
                break;
            }
        }
        if (!wasAdded) {
            speeches.add(this);
        }
        return speeches;
    }

}
