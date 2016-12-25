package com.senacor.controller;

import com.senacor.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

/**
 * Created by saba on 13.12.16.
 */
@RestController
@RequestMapping("/events/{eventID}/attendees")
public class AttendanceController {

    private final EventService eventService;

    @Autowired
    public AttendanceController(EventService eventService){
        this.eventService = eventService;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Boolean> getAttendeeStatus(@PathVariable("eventID") String eventID, @PathVariable("userId")String userId){
        System.out.println(eventService.getAttendeeStatus(eventID, userId));
        return Collections.singletonMap("isAttending", eventService.getAttendeeStatus(eventID, userId));
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addAttendeeToEvent(@PathVariable("eventID") String eventID, @PathVariable("userId")String userId){
        eventService.updateAttendeesList(eventID, userId);
    }
}
