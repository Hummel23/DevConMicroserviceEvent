package com.senacor.controller;


import com.senacor.model.Speech;
import com.senacor.service.SpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ResponseStatus(HttpStatus.CREATED)
    public Speech addSpeech(@PathVariable(value = "eventId") String eventID, @RequestBody Speech speech) {
        System.out.println("speechcontroller: addspeech method reached");

        return speechService.addSpeech(eventID, speech);
    }

    @RequestMapping(value = "/{speechId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Speech editSpeech(@PathVariable(value = "eventId") String eventID, @RequestBody Speech speech) {
        System.out.println("speechcontroller: in editspeech method");
        return speechService.editSpeech(eventID, speech);
    }


    //Speeches anlegen - insertSort beim Post/Put durchführen - comparable Interface bei Speeches wegfallen lassen

}
