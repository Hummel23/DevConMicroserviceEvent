package com.senacor.controller;

import com.senacor.model.Event;
import com.senacor.model.Speech;
import com.senacor.repository.EventRepository;
import com.senacor.repository.SpeechRepository;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    EventRepository eventRepository;

    @Mock
    SpeechRepository speechRepositoryMock;

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
        list.add(new Event("id"));
        list.add(new Event("id"));
        String expectedData= "Conference No.1";
        when(createEventControllerMock.listAllEvents()).thenReturn(list);
                mockMvc.perform(get("/event").contentType(MediaType.APPLICATION_JSON_UTF8))

                        .andExpect(jsonPath("$.[0].name", is("id")))

                        //Array now,  but wait for Json Object
                        //.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedData))

                        .andExpect(status().isOk());



    }

    @Test
    public void getEventSpeeches() throws Exception {
        ArrayList<Speech> list = new ArrayList<>();
        list.add(new Speech("ID"));
        list.add(new Speech("ID"));
        when(createEventControllerMock.getEventSpeeches("eventID")).thenReturn(list);
        mockMvc.perform(get("/event/ID"))
                .andExpect(status().isOk());
                //.andExpect(model().attribute("ID", list));
                //.andExpect(view().name("event/ID"));
}








}
