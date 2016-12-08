package com.senacor.controller;


import com.senacor.model.Event;
import com.senacor.model.Speech;
import com.senacor.service.AuthenticationService;
import com.senacor.service.EventService;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.startsWith;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by Marynasuprun on 31.10.2016.
 */

public class EventControllerTest {

    EventController createEventController;

    @Mock
    EventService eventService;

    @Mock
    private AuthenticationService authenticationService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        createEventController = new EventController(eventService, authenticationService);
    }

    // Test listAllEvents
    @Test
    public void getEvents() throws Exception {

        ArrayList<Event> list = new ArrayList<>();
        Event event1 = new Event();
        event1.setName("Conference");
        event1.setPlace("Berlin");
        Speech speech1 = new Speech(event1.getEventId());
        Speech speech2 = new Speech(event1.getEventId());
        event1.getSpeeches().add(speech1);
        event1.getSpeeches().add(speech2);
        list.add(event1);

        Event event2 = new Event();
        event2.setName("Conference2");
        list.add(event2);
        //list.add(new Event());

        when(eventService.listAllEvents()).thenReturn(list);
        List<Event> events = createEventController.listAllEvents();
        assertThat(events, hasSize(2));
        assertThat(events, hasItem(event1));
        verify(eventService, times(1)).listAllEvents();

    }

    //Test getSpeeches for Event
    @Test
    public void getEventSpeeches() throws Exception {

        ArrayList<Event> list = new ArrayList<>();
        Event event1 = new Event();
        event1.setName("Conference");
        event1.setPlace("Munich");

        Speech speech1 = new Speech(event1.getEventId());
        Speech speech2 = new Speech(event1.getEventId());
        speech1.setSpeaker("Dr. Obermann");
        speech1.setSpeechRoom("Nr: 234");
        event1.getSpeeches().add(speech1);
        event1.getSpeeches().add(speech2);
        list.add(event1);


        when(eventService.getSpeech(event1.getEventId(), speech1.getSpeechId())).thenReturn(speech1);
        //initialise new Speech for Test
        Speech speech = eventService.getSpeech(event1.getEventId(), speech1.getSpeechId());

        assertThat(speech.getSpeaker(), true);
        assertThat(event1.getSpeeches(), hasSize(2));
        assertThat(speech.getSpeaker(), startsWith("Dr."));
        verify(eventService, times(1)).getSpeech(event1.getEventId(), speech1.getSpeechId());

    }

    // Test CreateEvent
    @Test
    public void createEvent() throws Exception {

        Event event1 = new Event();
        event1.setName("Conference 20");
        event1.setEventId(UUID.randomUUID().toString());
        event1.setPlace("Example Street No. 20");
        event1.setDate(new LocalDate(2017, 6, 1));

        when(eventService.createEvent(event1)).thenReturn(event1);
        Event event = createEventController.createEvent(event1);

        //Object is not null, event is created
        assertNotNull(event);
        assertNotNull(event.getSpeeches());
        assertTrue(event.getPlace()=="Example Street No. 20");
        assertEquals("Conference 20", event.getName());
        verify(eventService, times(1)).createEvent(event);



    }

    //deleteEvent

    @Test
    public void deleteEvent() throws Exception {

        Event event1 = new Event();
        event1.setName("Conference 1");
        event1.setEventId(UUID.randomUUID().toString());
        Event event2 = new Event();
        event2.setName("Conference 2");
        event2.setEventId(UUID.randomUUID().toString());


        when(eventService.getEvent(event1.getEventId())).thenReturn(event1);
        Event event=createEventController.getEvent(event1.getEventId());

       // TODO event should be deleted
       // eventService.deleteEvent(event.getEventId());
        createEventController.deleteEvent(event.getEventId());

        verify(eventService, times(1)).getEvent(event.getEventId());
        verify(eventService, times(1)).deleteEvent(event.getEventId());

        System.out.println("Deleted Event " +event.getEventId());

    }

    //Linklogik noch testen

}





