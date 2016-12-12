package com.senacor.controller;

import com.senacor.model.Event;
import com.senacor.model.Speech;
import com.senacor.service.AuthenticationService;
import com.senacor.service.EventService;
import com.senacor.service.SpeechService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    public ResponseEntity<String> getCurrentEvent(@RequestHeader ("Authorization") String tokenId) {
        if (authenticationService.isAuthenticatedUser(tokenId)) {
            return new ResponseEntity<>(eventService.getCurrentEvent().getEventId(), HttpStatus.OK);

        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSpeech(@PathVariable("eventID") String eventID,@PathVariable("speechID")String speechID ) {
        speechService.deleteSpeech(eventID, speechID);
    }



    /*@RequestMapping(value = "/{eventID}/speeches/{speechID}", method = RequestMethod.DELETE)
    public Speech deleteSpeech(@PathVariable("eventID") String eventID, @PathVariable("speechID")String speechID){
        return speechService.getSpeech(eventID, speechID);
    }*/

    @RequestMapping(value = "/createEvent", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@RequestBody (required = false) Event event) {

        Event createdEvent = new Event();
        createdEvent.setName("Conference 10");
        createdEvent.setEventId(UUID.randomUUID().toString());
        createdEvent.setPlace("Example Street No. 10");
        createdEvent.setDate(new LocalDate(2017, 8, 1));

        return eventService.createEvent(createdEvent);

    }

    @RequestMapping(value = "/{eventID}/attendees/{userId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addAttendeeToEvent(@PathVariable("eventID") String eventID, @PathVariable("userId")String userId){
        eventService.updateAttendeesList(eventID, userId);
    }

    @RequestMapping(value = "/{eventID}/attendees/{userId}", method = RequestMethod.GET)
    public Map<String, Boolean> isAttending(@PathVariable("eventID") String eventID, @PathVariable("userId")String userId){
        return Collections.singletonMap("isAttending",eventService.checkAttendance(eventID, userId));
    }
    /*public ResponseEntity<Boolean> isAttending(@PathVariable("eventID") String eventID, @PathVariable("userId")String userId){

        boolean isAttending = eventService.checkAttendance(eventID, userId);
        return new ResponseEntity<>(isAttending, HttpStatus.OK);
    }*/

    //Speeches anlegen - insertSort beim Post/Put durchführen - comparable Interface bei Speeches wegfallen lassen
    //Create Event Object with parameters with http-POST Request

    /*@RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createEvent(@RequestBody Event event) {
        Event createdEvent = new Event();
        createdEvent.setName(event.getName());
        createdEvent.setEventId(UUID.randomUUID().toString());
        createdEvent.setPlace(event.getPlace());
        //createdEvent.setDate();
        eventRepository.save(createdEvent);
    } */


   /* @RequestMapping(value = "/{eventId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEvent(@PathVariable("eventId") String eventId,
                            @RequestBody (required=false) Event event) {
        Event existingEvent = eventRepository.findOne(id);
        existingEvent.setName(event.getName());
        existingEvent.setPlace(event.getPlace());
        // existingEvent.setDate(event.getDate());
        eventRepository.save(existingEvent);
    }*/


    @RequestMapping(value = "/{eventId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable("eventId") String eventId) {
        eventService.deleteEvent(eventId);
    }

}
