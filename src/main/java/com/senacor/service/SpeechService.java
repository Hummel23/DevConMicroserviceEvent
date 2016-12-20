package com.senacor.service;

import com.senacor.controller.EventController;
import com.senacor.model.Event;
import com.senacor.model.Speech;
import com.senacor.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by saba on 09.12.16.
 */
@Service
public class SpeechService {

    @Autowired
    EventRepository eventRepository;


    public void deleteSpeech(String eventId, String speechId) {
        Event event = eventRepository.findByEventId(eventId);
        List<Speech> speechList = event.getSpeeches();
        for(int i=0; i < speechList.size(); i++) {
            if (speechList.get(i).getSpeechId().equals(speechId)) {
                speechList.remove(i);
            }
        }
        event.setSpeeches(speechList);
        eventRepository.save(event);
    }

    public List<Speech> getAllSpeechesForEvent(String eventId) {
        Event event = eventRepository.findOne(eventId);
        for (Speech speech: event.getSpeeches()) {
            Link selflink = linkTo(EventController.class).slash(eventId + "/speeches/" + speech.getSpeechId()).withSelfRel();
            speech.add(selflink);
        }
        return event.getSpeeches();
    }

    public Speech getSpeech(String eventID, String speechID) {
        Event event = eventRepository.findOne(eventID);
        Speech selectedSpeech = new Speech();
        for (Speech speech : event.getSpeeches()) {
            if (speech.getSpeechId().equals(speechID)) {
                selectedSpeech = speech;
                break;
            }
        }
        Link selflink = linkTo(EventController.class).slash(eventID+"/speeches/" + speechID).withSelfRel();
        selectedSpeech.add(selflink);
        return selectedSpeech;

    }

    /*public void createSpeech(String eventID){
        Event event = eventRepository.findOne(eventID);
        Speech speech = new Speech();
        speech.setSpeechId("hi");
        speech.setSpeechTitle("Sooo good!");
        speech.setEndTime("20.00");
        Link selflink = linkTo(EventController.class).slash(eventID + "/speeches/" + speech.getSpeechId()).withSelfRel();
        speech.add(selflink);
        event.getSpeeches();
        eventRepository.save(event);
    }*/
}
