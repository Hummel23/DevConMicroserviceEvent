package com.senacor.controller;

import com.senacor.model.Event;
import com.senacor.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by saba on 21.10.16.
 */

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Event> listAllEvents() {
        return eventRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
        public Event sgetEvent(@PathVariable("id") String id){
        return eventRepository.findOne(id);
    }
}
