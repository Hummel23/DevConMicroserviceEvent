/*
package com.senacor.controller;


import com.senacor.model.Event;
import com.senacor.model.Speech;
import com.senacor.service.AuthenticationService;
import com.senacor.service.EventService;
import com.senacor.service.SpeechService;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.Mockito.*;

*/
/**
 * Created by Marynasuprun on 31.10.2016.
 *//*


public class EventControllerTest {

    EventController createEventController;

    @Mock
    EventService eventService;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    SpeechService speechService;



    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
       createEventController = new EventController(eventService,authenticationService);

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


    //Test for getEvent
    @Test
    public void getEvent () throws Exception {

        ArrayList<Event> list = new ArrayList<>();
        Event event1 = new Event();
        event1.setName("Conference");
        event1.setPlace("Berlin");
        event1.setDate(new LocalDate(2017, 6, 1));
        list.add(event1);

        Event event2 = new Event();
        event2.setPlace("Munich");
        event2.setDate(new LocalDate(2017, 8, 1));
        list.add(event2);

      //  when(eventService.getEvent(event1.getEventId())).thenReturn(event1);
      */
/*  //Event event=createEventController.getEvent(event1.getEventId());

        assertEquals("Conference", event.getName());
        assertTrue(event.getPlace()=="Berlin");
        verify(eventService, times(1)).getEvent(event1.getEventId());*//*



    }

    //Test for getSpeechesForEvent
    @Test
    public void getSpeechesForEvent() throws Exception {

        Event event1 = new Event();
        event1.setName("Conference");
        event1.setPlace("Berlin");
        event1.setDate(new LocalDate(2017, 6, 1));
        event1.setEventId("Event Id");

        ArrayList<Speech> speeches = new ArrayList<>();
        Speech speech1= new Speech(event1.getEventId());
        speech1.setSpeechId("SpeakerId1");
        speech1.setSpeaker("Dr.Whatson");
        speeches.add(speech1);

        Speech speech2= new Speech(event1.getEventId());
        speech2.setSpeechId("SpeakerId2");
        speech2.setSpeaker("Dr.Bro");
        speeches.add(speech2);

        event1.setSpeeches(speeches);



        when(speechService.getAllSpeechesForEvent(event1.getEventId())).thenReturn(speeches);
      //  List<Speech> speechesEvent = createEventController.getSpeechesForEvent(event1.getEventId());

      */
/*  assertThat(speechesEvent, hasSize(2));
        assertThat(speechesEvent, hasItem(speech1));
        assertThat(speechesEvent, hasItem(speech2));
        assertNotNull(speechesEvent);
        assertTrue(speech1.getSpeaker()=="Dr.Whatson");

        verify(speechService, times(1)).getAllSpeechesForEvent(event1.getEventId());*//*



    }



    // Test for CreateEvent
    @Test
    public void createEvent() throws Exception {

       */
/* Event event1 = new Event();
        event1.setName("Conference 20");
        //event1.setEventId(UUID.randomUUID().toString());
        event1.setPlace("Example Street No. 20");
        event1.setDate(new LocalDate(2017, 6, 1));

        when(eventService.addEvent(event1)).thenReturn(event1);
        Event event = createEventController.createEvent(event1);

        //Object is not null, event is created
        assertNotNull(event);
        assertNotNull(event.getSpeeches());
        assertTrue(event.getPlace()=="Example Street No. 20");
        assertEquals("Conference 20", event.getName());
        verify(eventService, times(1)).addEvent(event);
*//*



    }


    // Test for delete Speech
    @Test
    public void deleteSpeech() throws Exception {

        Event event1 = new Event();
        event1.setName("Conference 1");
        event1.setEventId(UUID.randomUUID().toString());
        Event event2 = new Event();
        event2.setName("Conference 2");
        event2.setEventId(UUID.randomUUID().toString());

        Speech speech1 = new Speech();
        Speech speech2 = new Speech();
        event1.getSpeeches().add(speech1);
        event1.getSpeeches().add(speech2);

       // when(eventService.getEvent(event1.getEventId())).thenReturn(event1);
       */
/* Event event=createEventController.getEvent(event1.getEventId());
        createEventController.deleteSpeech(event1.getEventId(), speech1.getSpeechId());

        assertNull(speech1.getSpeechId());
        verify(speechService, times(1)).deleteSpeech(event.getEventId(), speech1.getSpeechId());*//*


    }

    //Linklogik noch testen

}





*/
