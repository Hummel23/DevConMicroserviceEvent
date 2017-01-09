package com.senacor.service;

import com.senacor.model.Event;
import com.senacor.model.NaturalPerson;
import com.senacor.repository.EventRepository;
import org.springframework.beans.factory.NamedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by saba on 26.12.16.
 */
@Service
public class AttendanceService {
    EventRepository eventRepository;

    final String attendanceUri = "http://localhost:8081/naturalPerson/";
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public AttendanceService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    public void updateAttendeesList(String eventID, String userId) {
        Event event = eventRepository.findByEventId(eventID);
        List<NaturalPerson> attendees = event.getAttendees();
        boolean attendeeIsRemoved = false;
        for (int i=0; i<attendees.size(); i++) {
            if (attendees.get(i).getUserId().equals(userId)){
                attendees.remove(i);
                attendeeIsRemoved=true;
            }
        }
        if (!attendeeIsRemoved){
            ResponseEntity<NaturalPerson> response = restTemplate.getForEntity( attendanceUri + userId, NaturalPerson.class);
            attendees.add(response.getBody());
        }
        event.setAttendees(attendees);
        eventRepository.save(event);
    }

    public boolean getAttendeeStatus(String eventID, String userId) {
        List<NaturalPerson> attendees = eventRepository.findByEventId(eventID).getAttendees();
        for (int i = 0; i < attendees.size(); i++) {
            if (attendees.get(i).getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }
}
