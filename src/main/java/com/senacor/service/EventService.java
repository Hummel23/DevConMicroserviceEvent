package com.senacor.service;

import com.senacor.controller.AttendanceController;
import com.senacor.controller.EventController;
import com.senacor.controller.SpeechController;
import com.senacor.model.Event;
import com.senacor.model.Speech;
import com.senacor.repository.EventRepository;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by saba on 07.11.16.
 */
@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ValidationService validationService;

    public Event getCurrentEvent() {

        List<Event> events = eventRepository.findAllByOrderByDateDesc();
        if (!events.isEmpty()) {
            List<Event> withoutPastEvents = getOnlyPresentEvents(events);
            Event currentEvent = withoutPastEvents.get(withoutPastEvents.size() - 1);
            Link selflink = linkTo(EventController.class).slash(currentEvent.getEventId()).withSelfRel();
            currentEvent.add(selflink);
            return currentEvent;
        }

        return null;
    }

    private List<Event> getOnlyPresentEvents(List<Event> events) {
        List<Event> presentEvents = new ArrayList<>();

        for (Event event: events) {
            if (event.getDate().isAfter(LocalDate.now())
                    || event.getDate().equals(LocalDate.now())) {
                presentEvents.add(event);
            }
        }

        return presentEvents;

    }

    public Event getEvent(String eventId, String userId) {
        Event event = eventRepository.findOne(eventId);
        Link selflink = linkTo(EventController.class).slash(eventId).withSelfRel();
        event.add(selflink);
        List<Speech> methodLinkBuilder = methodOn(SpeechController.class).getSpeechesForEvent(eventId);
        Link speechLink = linkTo(methodLinkBuilder).withRel("speeches");
        event.add(speechLink);
        Map<String, Boolean> methodLinkBuilder1 = methodOn(AttendanceController.class).getAttendeeStatus(event.getEventId(), userId);
        Link attendanceLink = linkTo(methodLinkBuilder1).withRel("attendance");
        event.add(attendanceLink);
        return event;
    }


    public List<Event> listAllEvents() {
        List<Event>events = eventRepository.findAllByOrderByDateDesc();
        for (Event event : events) {
            System.out.println(event.getEventId());
            Link selflink = linkTo(EventController.class).slash(event.getEventId()).withSelfRel();
            event.add(selflink);
            System.out.println(event.getEventId());
        }
        return events;
    }

    public Event addEvent(Event event) {

        if (validationService.isNotCollidingWithOtherEvent(eventRepository.findAll(), event)) {
            eventRepository.save(event);
            System.out.println("in add event, event saved, eventId = " + event.getEventId());
            Link selflink = linkTo(EventController.class).slash(event.getEventId()).withSelfRel();
            event.add(selflink);
            return event;
        }
        return null;
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


    public Event updateEvent(Event event) {
        if (validationService.isNotCollidingWithOtherEvent(eventRepository.findAll(), event)) {
            System.out.println("eventservice: event will be updated");
            eventRepository.save(event);
            return event;
        } else {
            System.out.println("eventservice: event seems to be colliding");
            return null;
        }
    }
}

