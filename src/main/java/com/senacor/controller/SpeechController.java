package com.senacor.controller;


import com.senacor.model.Speech;
import com.senacor.service.AuthenticationService;
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
    private final AuthenticationService authenticationService;

    @Autowired
    public SpeechController(SpeechService speechService, AuthenticationService authenticationService) {
        this.speechService = speechService;
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Speech> getSpeechesForEvent( @PathVariable(value = "eventId") String eventID) {
            return speechService.getAllSpeechesForEvent(eventID);
    }

    @RequestMapping(value = "/{speechID}", method = RequestMethod.GET)
    public ResponseEntity<Speech> getSpeech(@RequestHeader("Authorization") String tokenId, @PathVariable(value = "eventId") String eventID, @PathVariable("speechID")String speechID) {
        if (authenticationService.isAuthenticatedUser(tokenId)) {
            return new ResponseEntity<Speech>(speechService.getSpeech(eventID, speechID), HttpStatus.OK);
        }else{
            return new ResponseEntity<Speech>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/{speechID}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSpeech(@RequestHeader("Authorization") String tokenId, @PathVariable(value = "eventId") String eventID,@PathVariable("speechID")String speechID ) {
            if (authenticationService.isAuthenticatedUser(tokenId)) {

                speechService.deleteSpeech(eventID, speechID);
            }
    }

    @RequestMapping(value = "/createSpeech", method = RequestMethod.POST)
    public ResponseEntity addSpeech(@RequestHeader("Authorization") String tokenId, @PathVariable(value = "eventId") String eventID, @RequestBody Speech speech) {
        if (authenticationService.isAuthenticatedUser(tokenId)) {

            Speech savedSpeech = speechService.addSpeech(eventID, speech);
            if (savedSpeech != null) {
                return new ResponseEntity(savedSpeech, HttpStatus.CREATED);
            } else {
                HttpHeaders headers = new HttpHeaders();
                headers.add("409-Status-Reason: ", "Validation failed");
                return new ResponseEntity(speech, headers, HttpStatus.CONFLICT);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/{speechId}", method = RequestMethod.PUT)
    public ResponseEntity<Speech> editSpeech(@RequestHeader("Authorization") String tokenId, @PathVariable(value = "eventId") String eventID, @RequestBody Speech speech) {
        if (authenticationService.isAuthenticatedUser(tokenId)) {

            Speech editedSpeech = speechService.editSpeech(eventID, speech);
            if (editedSpeech != null) {
                return new ResponseEntity<>(editedSpeech, HttpStatus.OK);
            } else {
                HttpHeaders headers = new HttpHeaders();
                headers.add("409-Status-Reason: ", "Validation failed");
                Link selflink = linkTo(EventController.class).slash(eventID + "/speeches/" + speech.getSpeechId()).withSelfRel();
                speech.add(selflink);
                return new ResponseEntity<>(speech, headers, HttpStatus.CONFLICT);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }


    //Speeches anlegen - insertSort beim Post/Put durchführen - comparable Interface bei Speeches wegfallen lassen

}
