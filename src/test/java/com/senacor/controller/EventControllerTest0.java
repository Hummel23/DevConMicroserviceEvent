package com.senacor.controller;

import com.senacor.repository.EventRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.web.WebMvcProperties;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.servlet.View;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

        mockMvc.perform(get("/event"))
        .andExpect(status().isOk());

    }


}
