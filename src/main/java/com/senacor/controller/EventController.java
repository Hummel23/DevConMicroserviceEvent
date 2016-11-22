package com.senacor.controller;

import com.senacor.model.Event;
import com.senacor.model.Speech;
import com.senacor.model.User;
import com.senacor.service.EventService;
import com.senacor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by saba on 21.10.16.
 */

@RestController
@RequestMapping("/event")
public class EventController {


    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Iterable<Event> listAllEvents() {
        return eventService.listAllEvents();
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseEntity<User> login(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "password", required=false) String password){
        System.out.println(username + password);
        User user = new User(username, password);
        ResponseEntity<User> response = userService.authenticateUser(user);
        return response;
            /*ResponseEntity<User> entity = new ResponseEntity<>(user, HttpStatus.OK);
            return entity;
        } else{
            ResponseEntity<User> entity = new ResponseEntity<>(user, HttpStatus.UNAUTHORIZED);
            return entity;
        }*/

/*        User user = new User(username, password);
        user.setSuccess(false);
        System.out.println(user.getPassword());
        System.out.println(user.getUsername());
        user.setSuccess(userService.authenticateUser(user));
        return user;*/
        /*System.out.println(isValidUser);
        if(isValidUser) {*/

   /*         Event event = eventService.getCurrentEvent();
            try {*/
/*
                response.setHeader("status", "true");
*/
/*               response.sendRedirect("http://localhost:8080/event/" + event.getEventId());*/
            /*} catch (Exception e) {
                e.printStackTrace();
            }*/
//        }

    }

    @RequestMapping(value="/currentEvent", method = RequestMethod.GET)
    public void getCurrentEvent(HttpServletResponse response){
        Event event = eventService.getCurrentEvent();
        try {
            response.sendRedirect("http://141.45.208.68:8080/event/" + event.getEventId());
        } catch (Exception e) {
            e.printStackTrace();
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
        return eventService.getAllSpeechesForEvent(eventID);
    }

    @RequestMapping(value = "/{eventID}/speeches/{speechID}", method = RequestMethod.GET)
    public Speech getSpeech(@PathVariable("eventID") String eventID, @PathVariable("speechID")String speechID){
        return eventService.getSpeech(eventID, speechID);
    }

/*    //Create Event Object with parameters with http-POST Request
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createEvent(@RequestBody Event event) {
        Event createdEvent = new Event();
        createdEvent.setName(event.getName());
        createdEvent.setPlace(event.getPlace());
        //createdEvent.setDate();
        eventRepository.save(createdEvent);
    }*/
/*    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEvent(@PathVariable("id") String id, @RequestBody Event event) {
        Event existingEvent = eventRepository.findOne(id);
        existingEvent.setName(event.getName());
        existingEvent.setPlace(event.getPlace());
       // existingEvent.setDate(event.getDate());
        eventRepository.save(existingEvent);
    }
    */

    @RequestMapping(value = "/{eventId}", method = RequestMethod.DELETE)
    public void deleteEvent(@PathVariable("eventId") String eventId) {
        eventService.deleteEvent(eventId);
    }

}
