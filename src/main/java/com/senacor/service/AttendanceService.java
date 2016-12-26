package com.senacor.service;

import com.senacor.model.Event;
import com.senacor.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by saba on 26.12.16.
 */
@Service
public class AttendanceService {
    EventRepository eventRepository;

    @Autowired
    public AttendanceService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    public void updateAttendeesList(String eventID, String userId) {
        Event event = eventRepository.findByEventId(eventID);
        List<String> attendees = event.getAttendees();
        boolean attendeeIsRemoved = false;
        for (int i=0; i<attendees.size(); i++) {
            if (attendees.get(i).equals(userId)){
                System.out.println("not attending anymore");
                attendees.remove(i);
                attendeeIsRemoved=true;
            }
        }
        if (!attendeeIsRemoved){
            System.out.println("attending now.");
            attendees.add(userId);
        }
        event.setAttendees(attendees);
        eventRepository.save(event);
    }

    public boolean getAttendeeStatus(String eventID, String userId) {
        List<String> attendees = eventRepository.findByEventId(eventID).getAttendees();
        for (int i = 0; i < attendees.size(); i++) {
            if (attendees.get(i).equals(userId)) {
                return true;
            }
        }
        return false;
    }
}
