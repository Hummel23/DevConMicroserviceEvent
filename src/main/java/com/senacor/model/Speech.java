package com.senacor.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.ResourceSupport;

@Document
public class Speech extends ResourceSupport implements Comparable<Speech>{


    @Id
    private String speechId;
    private String speechTitle;
    private String startTime;
    private String endTime;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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

    @Override
    public int compareTo(Speech o) {
        return 0;
    }
}
