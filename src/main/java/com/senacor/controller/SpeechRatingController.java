package com.senacor.controller;

import com.senacor.model.Speech;
import com.senacor.service.SpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by saba on 23.01.17.
 */

@RestController
@RequestMapping("/speeches")
public class SpeechRatingController {

    private final SpeechService speechService;

    @Autowired
    public SpeechRatingController(SpeechService speechService) {
        this.speechService = speechService;
    }

    @RequestMapping(value = "/{speechId}", method = RequestMethod.GET)
    public ResponseEntity<Speech> getSpeechesForEvent(@PathVariable(value = "speechId") String speechId) {
        Speech speech = speechService.getSpeechForRating(speechId);
        if (speech != null) {
            System.out.println("speeech found and returned.");
            return new ResponseEntity<>(speech, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
