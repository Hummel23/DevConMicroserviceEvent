package com.senacor.controller;

import com.senacor.model.Speech;
import com.senacor.service.EventService;
import com.senacor.service.SpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by saba on 13.01.17.
 */
@RestController
@RequestMapping("ratingData/")
public class RatingController {

    EventService eventService;
    SpeechService speechService;

    @Autowired
    public RatingController(EventService eventService, SpeechService speechService) {
        this.eventService = eventService;
        this.speechService = speechService;
    }

    @RequestMapping(value = "/{eventId}/{speechID}", method = RequestMethod.GET)
    public ResponseEntity<Speech> getSpeech(@PathVariable(value = "eventId") String eventId, @PathVariable("speechID")String speechId){

        Speech speech = speechService.getSpeech(eventId, speechId);
        if (speech != null) {
            return new ResponseEntity<Speech>(speech, HttpStatus.OK);
        }else{
            return new ResponseEntity<Speech>(HttpStatus.NOT_FOUND);
        }

    }
}
