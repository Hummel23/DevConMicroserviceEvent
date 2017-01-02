package com.senacor.controller;


import com.senacor.model.Speech;
import com.senacor.service.SpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by saba on 13.12.16.
 */
@RestController
@RequestMapping("/events/{eventId}/speeches")
public class SpeechController {

    private final SpeechService speechService;

    @Autowired
    public SpeechController(SpeechService speechService) {
        this.speechService = speechService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Speech> getSpeechesForEvent(@PathVariable(value = "eventId") String eventID) {
        return speechService.getAllSpeechesForEvent(eventID);
    }

    @RequestMapping(value = "/{speechID}", method = RequestMethod.GET)
    public Speech getSpeech(@PathVariable(value = "eventId") String eventID, @PathVariable("speechID")String speechID){
        return speechService.getSpeech(eventID, speechID);
    }

    @RequestMapping(value = "/{speechID}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSpeech(@PathVariable(value = "eventId") String eventID,@PathVariable("speechID")String speechID ) {
        speechService.deleteSpeech(eventID, speechID);
    }

    @RequestMapping(value = "/createSpeech", method = RequestMethod.POST)
    public ResponseEntity addSpeech(@PathVariable(value = "eventId") String eventID, @RequestBody Speech speech) {
        System.out.println("speechcontroller: addspeech method reached");
        Speech savedSpeech = speechService.addSpeech(eventID, speech);
        if (savedSpeech != null) {
            return new ResponseEntity(savedSpeech, HttpStatus.CREATED);
        }else{
            HttpHeaders headers = new HttpHeaders();
            headers.add("409-Status-Reason: ",  "Validation failed");
            return new ResponseEntity(speech, headers, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/{speechId}", method = RequestMethod.PUT)
    public ResponseEntity<Speech> editSpeech(@PathVariable(value = "eventId") String eventID, @RequestBody Speech speech) {
        System.out.println("speechcontroller: in editspeech method");
        Speech editedSpeech = speechService.editSpeech(eventID, speech);
        if (editedSpeech != null) {
            return new ResponseEntity<>(editedSpeech, HttpStatus.OK);
        }else{
            HttpHeaders headers = new HttpHeaders();
            headers.add("409-Status-Reason: ", "Validation failed");
            Link selflink = linkTo(EventController.class).slash(eventID + "/speeches/" + speech.getSpeechId()).withSelfRel();
            speech.add(selflink);
            return new ResponseEntity<>(speech, headers, HttpStatus.CONFLICT);
        }
    }


    //Speeches anlegen - insertSort beim Post/Put durchführen - comparable Interface bei Speeches wegfallen lassen

}
