package com.senacor.controller;


import com.senacor.model.Event;
import com.senacor.model.Speech;
import com.senacor.service.EventService;
import com.senacor.service.AuthenticationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
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
        createEventController= new EventController(eventService, authenticationService);
    }

    @Test
    public void getEvent() throws Exception {

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
        assertThat(events,hasSize(2));

        verify(eventService,times(1)).listAllEvents();

    }

    //Linklogik noch testen

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
        Speech speech = eventService.getSpeech(event1.getEventId(), speech1.getSpeechId());

        assertThat(speech1.getSpeaker(), true);


        verify(eventService, times(1)).getSpeech(event1.getEventId(), speech1.getSpeechId());

    }


}


