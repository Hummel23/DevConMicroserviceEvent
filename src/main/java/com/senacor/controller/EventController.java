package com.senacor.controller;

import com.senacor.model.Event;
import com.senacor.model.Speech;
import com.senacor.service.EventService;
import com.senacor.service.AuthenticationService;
import com.senacor.service.SpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by saba on 21.10.16.
 */

@RestController
@RequestMapping("/event")
public class EventController {


    private final EventService eventService;
    private final AuthenticationService authenticationService;
    private final SpeechService speechService;

    @Autowired
    public EventController(EventService eventService, AuthenticationService authenticationService, SpeechService speechService) {
        this.eventService = eventService;
        this.authenticationService = authenticationService;
        this.speechService = speechService;
    }



    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Event> listAllEvents() {
        return eventService.listAllEvents();
    }


    @RequestMapping(value="/currentEvent", method = RequestMethod.GET)
    public ResponseEntity<Event> getCurrentEvent(@RequestHeader ("Authorization") String tokenId) {
        if (authenticationService.isAuthenticatedUser(tokenId)) {
            return new ResponseEntity<>(eventService.getCurrentEvent(), HttpStatus.OK);

        }else{
            return new ResponseEntity<>(eventService.getCurrentEvent(), HttpStatus.UNAUTHORIZED);

        }
    }

    @RequestMapping(value = "/{eventID}", method = RequestMethod.GET)
    public Event getEvent(@PathVariable("eventID") String eventID){
        Event event = eventService.getEvent(eventID);
        System.out.println(event.getEventId());
        return event;
    }

    @RequestMapping(value = "/{eventID}/speeches", method = RequestMethod.GET)
    public List<Speech> getSpeechesForEvent(@PathVariable("eventID") String eventID) {
        return speechService.getAllSpeechesForEvent(eventID);
    }

    @RequestMapping(value = "/{eventID}/speeches/{speechID}", method = RequestMethod.GET)
    public Speech getSpeech(@PathVariable("eventID") String eventID, @PathVariable("speechID")String speechID){
        return speechService.getSpeech(eventID, speechID);
    }

    @RequestMapping(value = "/{eventID}/speeches/{speechID}", method = RequestMethod.DELETE)
    public Speech deleteSpeech(@PathVariable("eventID") String eventID, @PathVariable("speechID")String speechID){
        return speechService.getSpeech(eventID, speechID);
    }

    //Speeches anlegen - insertSort beim Post/Put durchführen - comparable Interface bei Speeches wegfallen lassen

/*    //Create Event Object with parameters with http-POST Request
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createEvent(@RequestBody Event event) {
        Event createdEvent = new Event();
        createdEvent.setName(event.getName());#
        createdEvent.setEventId(UUID.randomUUID().toString());
        createdEvent.setPlace(event.getPlace());
        //createdEvent.setDate();
        eventRepository.save(createdEvent);
    }*/
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEvent(@PathVariable("id") String id, @RequestBody Event event) {
        eventService.editEvent(event);
    }


    @RequestMapping(value = "/{eventId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable("eventId") String eventId) {
        eventService.deleteEvent(eventId);
    }

}
