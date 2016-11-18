package com.senacor.controller;


import com.senacor.model.Event;
import com.senacor.model.Speech;
import com.senacor.service.EventService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


/**
 * Created by Marynasuprun on 31.10.2016.
 */
public class EventControllerTest0 {

    @InjectMocks
    EventController createEventControllerMock;

    @Mock
    EventService eventService;

    @Mock
    View mockView;

    MockMvc mockMvc;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(createEventControllerMock)
                .setSingleView(mockView)
                .build();


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

        when(createEventControllerMock.listAllEvents()).thenReturn(list);
        mockMvc.perform(get("/event/list").contentType(MediaType.APPLICATION_JSON_UTF8))


                .andDo(print())
                .andExpect(jsonPath("$.[0].place").value("Berlin"))
                .andExpect(jsonPath("$.[1].name").value("Conference2"))
                .andExpect(jsonPath("$.[1].place", is(event2.getPlace())))
                .andExpect(jsonPath("$[0].name", is(event1.getName())))
                .andExpect(jsonPath("$[0]eventId", is(nullValue())))
                .andExpect(jsonPath("$[0].links").exists())

                .andExpect(status().isOk());



    }

    @Test
    public void getEventSpeeches() throws Exception {
        ArrayList<Speech> list = new ArrayList<>();
        list.add(new Speech("ID"));
        list.add(new Speech("ID"));
        when(createEventControllerMock.getSpeechesForEvent("eventID")).thenReturn(list);
        mockMvc.perform(get("/event/ID/speeches"))
                .andExpect(status().isOk());
        //.andExpect(model().attribute("ID", list));
        //.andExpect(view().name("event/ID"));
    }
}


