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

    @Autowired
    ValidationService validationService;


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

    public Speech addSpeech(String eventId, Speech speech) {
        System.out.println("in speechservice: add speech method");
        Event event = eventRepository.findByEventId(eventId);
        if (event != null) {
            if(validationService.isNotCollidingWithOtherSpeech(event, speech)){
                speech.setEventID(eventId);
                event.insertSpeechSorted(speech);
                eventRepository.save(event);
                return speech;
            }
        }
        return null;
    }

    public Speech editSpeech(String eventID, Speech speech) {
        System.out.println("in speechservice: editSpeech");
        Event event = eventRepository.findByEventId(eventID);
        if (event != null) {
            List<Speech> speeches = event.getSpeeches();
            Speech currentSpeech = null;
            for (int i = 0; i < speeches.size(); i++) {
                if (speeches.get(i).getSpeechId().equals(speech.getSpeechId())) {
                    System.out.println("speech found");
                    currentSpeech = speeches.get(i);
                    System.out.println("speech removed");
                    speeches.remove(i);
                    event.setSpeeches(speeches);
                    eventRepository.save(event);
                    break;
                }
            }
            if (currentSpeech != null) {
                if (validationService.isNotCollidingWithOtherSpeech(event, speech)) {
                    currentSpeech.setSpeaker(speech.getSpeaker());
                    currentSpeech.setSpeechRoom(speech.getSpeechRoom());
                    currentSpeech.setSpeechTitle(speech.getSpeechTitle());
                    currentSpeech.setStartTime(speech.getStartTime());
                    currentSpeech.setEndTime(speech.getEndTime());
                    System.out.println("adding speech at same position");
                    speeches.add(speech);

                    System.out.println("updating speeches of event");
                    event.setSpeeches(speeches);

                    System.out.println("saving event");
                    eventRepository.save(event);
                    return currentSpeech;
                }
            }
        }

        return null;
        }



}
