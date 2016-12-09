package com.senacor.service;

import com.senacor.controller.EventController;
import com.senacor.model.Event;
import com.senacor.model.Speech;
import com.senacor.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by saba on 07.11.16.
 */
@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public Event getCurrentEvent() {
        List<Event> events = eventRepository.findAll();
        Collections.sort(events);
        Event currentEvent = events.get(events.size() - 1);
        Link selflink = linkTo(EventController.class).slash(currentEvent.getEventId()).withSelfRel();
        currentEvent.add(selflink);
        List<Speech> methodLinkBuilder = methodOn(EventController.class).getSpeechesForEvent(currentEvent.getEventId());
        Link speechLink = linkTo(methodLinkBuilder).withRel("speeches");
        currentEvent.add(speechLink);
        return currentEvent;
    }

    public Event getEvent(String eventId) {
        Event event = eventRepository.findOne(eventId);
        Link selflink = linkTo(EventController.class).slash(eventId).withSelfRel();
        event.add(selflink);
        List<Speech> methodLinkBuilder = methodOn(EventController.class).getSpeechesForEvent(eventId);
        Link speechLink = linkTo(methodLinkBuilder).withRel("speeches");
        event.add(speechLink);
        return event;
    }


    public List<Event> listAllEvents() {
        List<Event>events = eventRepository.findAll();
        for (Event event : events) {
            System.out.println(event.getEventId());
            Link selflink = linkTo(EventController.class).slash(event.getEventId()).withSelfRel();
            event.add(selflink);
            System.out.println(event.getEventId());
        }
        return events;
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public void editEvent(Event event) {
        eventRepository.delete(event.getEventId());
        eventRepository.save(event);
    }

    public void deleteEvent(String eventId) {
        eventRepository.delete(eventId);
    }

    public void deleteAllEvents() {
        eventRepository.deleteAll();
    }


}
