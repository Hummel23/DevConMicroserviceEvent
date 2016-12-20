package com.senacor.controller;

import com.senacor.model.Event;
import com.senacor.service.AuthenticationService;
import com.senacor.service.EventService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by saba on 21.10.16.
 */

@RestController
//@RequestMapping("/event")
@RequestMapping("/events")
public class EventController {


    private final EventService eventService;
    private final AuthenticationService authenticationService;

    @Autowired
    public EventController(EventService eventService, AuthenticationService authenticationService) {
        this.eventService = eventService;
        this.authenticationService = authenticationService;
    }

    //@RequestMapping(value = "", method = RequestMethod.GET)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Event> listAllEvents() {
        return eventService.listAllEvents();
    }


    @RequestMapping(value="/currentEvent", method = RequestMethod.GET)
    public ResponseEntity<Event> getCurrentEvent(@RequestHeader ("Authorization") String tokenId) {
        if (authenticationService.isAuthenticatedUser(tokenId)) {
            return new ResponseEntity<>(eventService.getCurrentEvent(), HttpStatus.OK);

        }else{
           return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        }
    }

    @RequestMapping(value = "/{eventID}", method = RequestMethod.GET)
    public ResponseEntity<Event> getEvent(@RequestHeader ("Authorization") String tokenId, @PathVariable("eventID") String eventID){
        if (authenticationService.isAuthenticatedUser(tokenId)) {
            String userId = authenticationService.getUserId(tokenId);
            return new ResponseEntity<>(eventService.getEvent(eventID, userId), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        }
    }


    @RequestMapping(value = "/createEvent", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@RequestBody (required = false) Event event) {

        Event createdEvent = new Event();
        createdEvent.setName("");
        //createdEvent.setEventId(UUID.randomUUID().toString());
        createdEvent.setPlace("");
        createdEvent.setDate(new LocalDate(2017, 8, 1));
        return eventService.createEvent(createdEvent);

    }

    @RequestMapping(value = "/{eventId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable("eventId") String eventId, @RequestHeader ("Authorization") String tokenId) {
        if (authenticationService.isAuthenticatedUser(tokenId)) {
            eventService.deleteEvent(eventId);
        }
    }

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


   /*@RequestMapping(value = "/{eventId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEvent(@PathVariable("eventId") String eventId,
                            @RequestBody (required=false) Event event) {
        Event existingEvent = eventService.getEvent(eventId);
        existingEvent.setName(event.getName());
        existingEvent.setPlace(event.getPlace());
        existingEvent.setDate(event.getDate());
        //eventRepository.save(existingEvent);
    }*/



}
