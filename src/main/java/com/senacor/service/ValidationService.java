package com.senacor.service;

import com.senacor.model.Event;
import com.senacor.model.Speech;
import com.senacor.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

/**
 * Created by saba on 30.12.16.
 */
@Service
public class ValidationService {

    public boolean isNotCollidingWithOtherSpeech(List<Speech>speeches, Speech speech) {

        LocalTime newSpeechStart = speech.getStartTime();
        LocalTime newSpeechEnd = speech.getEndTime();

        for (Speech sp : speeches) {
            if (! sp.getSpeechId().equals(speech.getSpeechId())) {
                if (!(speechIsBefore(newSpeechStart, newSpeechEnd, sp.getStartTime()) ||
                        speechIsAfter(newSpeechStart, sp.getEndTime()))) {
                    return false;
                }
            }

        }
        return true;
    }


    private boolean speechIsBefore(LocalTime newSpeechStart, LocalTime newSpeechEnd, LocalTime startTime) {
        return newSpeechStart.isBefore(startTime) && newSpeechEnd.isBefore(startTime);
    }

    private boolean speechIsAfter(LocalTime newSpeechStart, LocalTime endTime) {
        return newSpeechStart.isAfter(endTime);
    }
}
