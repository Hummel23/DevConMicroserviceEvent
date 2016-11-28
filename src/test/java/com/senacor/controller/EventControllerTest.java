package com.senacor.controller;


import com.senacor.model.Event;
import com.senacor.model.Speech;
import com.senacor.service.EventService;
import com.senacor.service.UserService;
import com.senacor.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


/**
 * Created by Marynasuprun on 31.10.2016.
 */

public class EventControllerTest {

    EventController createEventController;

    @Mock
    EventService eventService;

    @Mock
    private UserService userService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        createEventController= new EventController(eventService,userService);
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
        list.add(new Event());

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

                mockMvc.perform(get("/event/eventID/speeches").contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(status().isOk())
                        .andDo(print());
                        assertEquals("Nr: 234", speech1.getSpeechRoom());
                        assertEquals("Dr. Obermann", speech1.getSpeaker());
                        assertEquals( new Speech(), speech2);
            }

    @Test
    public void getLogin() throws Exception {

        String username = "Saba";
        String password = "123";
        //User user = userService.authenticateUser(new User(username, password));

       ResultActions result = mockMvc.perform(
                post("/event/login")

                // mockMvc.perform(post("/event/login")
                .param("username", username)
                .param("password", password))
                 // .andExpect(MockMvcResultMatchers.status().isOk())

                .andDo(print());
                 Assert.assertNotNull(result);



        }

    @Test
    public void getCurrentEvent() throws Exception {

        mockMvc.perform(get("/event/currentEvent").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print());


    }




}


