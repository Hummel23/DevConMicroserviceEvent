package com.senacor.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

/**
 * Created by saba on 21.10.16.
 */

public class Speech {

    @Id
    private String id;
    private String speechTitle;
    private LocalDate speechTime;
    /*
    Objekte werden dann von Werten wie folgt gebildet:LocalDate.of(2012, Month.DECEMBER, 12); // from values
     */
    private String speechLocation;
    private String speechSpeaker;
    private String speechSummary;
    private String speechResumeSpeaker;

    public Speech() {
    }

    public String getSpeechTitle() {
        return speechTitle;
    }

    public void setSpeechTitle(String speechTitle) {
        this.speechTitle = speechTitle;
    }

    public LocalDate getSpeechTime() {
        return speechTime;
    }

    public void setSpeechTime(LocalDate speechTime) {
        this.speechTime = speechTime;
    }

    public String getSpeechLocation() {
        return speechLocation;
    }

    public void setSpeechLocation(String speechLocation) {
        this.speechLocation = speechLocation;
    }

    public String getSpeechSpeaker() {
        return speechSpeaker;
    }

    public void setSpeechSpeaker(String speechSpeaker) {
        this.speechSpeaker = speechSpeaker;
    }

    public String getSpeechSummary() {
        return speechSummary;
    }

    public void setSpeechSummary(String speechSummary) {
        this.speechSummary = speechSummary;
    }

    public String getSpeechResumeSpeaker() {
        return speechResumeSpeaker;
    }

    public void setSpeechResumeSpeaker(String speechResumeSpeaker) {
        this.speechResumeSpeaker = speechResumeSpeaker;
    }

}
