package com.senacor.service;

import com.senacor.model.Speech;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;

/**
 * Created by saba on 13.01.17.
 */

@Service
public class RatingService {
    final String ratingUri = "http://localhost:8082/dataUpdate";
    RestTemplate restTemplate;

    public RatingService() {

    }

    public ResponseEntity<Speech> createSpeech(Speech speech) throws MalformedURLException {
        restTemplate = new RestTemplate();
        HttpEntity<Speech> request = new HttpEntity<>(speech);
        return restTemplate.postForEntity(ratingUri + "/createSpeech", request, Speech.class);
    }

    public void editSpeech(Speech speech) throws MalformedURLException {
        restTemplate = new RestTemplate();
        HttpEntity<Speech> request = new HttpEntity<>(speech);
        restTemplate.put(ratingUri + "/editSpeech/" + speech.getSpeechId(), request);
    }

    public void deleteSpeech(Speech speech) throws MalformedURLException {
        restTemplate = new RestTemplate();
        restTemplate.delete(ratingUri + "/deleteSpeech/" + speech.getSpeechId());
    }

}
