package com.senacor.controller;

import com.senacor.model.Event;
import com.senacor.model.Speech;
import com.senacor.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Iterable<Event> listAllEvents() {
        return eventService.listAllEvents();
    }

    @RequestMapping(value="/currentEvent", method = RequestMethod.GET)
    public void getCurrentEvent(HttpServletResponse response){
        Event event = eventService.getCurrentEvent();
        try {
            response.sendRedirect("http://localhost:8080/event/" + event.getEventId());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/{eventID}", method = RequestMethod.GET)
        public Event getEvent(@PathVariable("eventID") String eventID){
        return eventService.getEvent(eventID);
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
