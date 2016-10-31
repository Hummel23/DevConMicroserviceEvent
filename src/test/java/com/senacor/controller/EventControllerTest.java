package com.senacor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senacor.model.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.*;

/**
 * Created by Berlina on 29.10.16.
 */
/*@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EventController createEventControllerMock;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void listAllEvents() throws Exception {

    }

    @Test
    public void getEvent() throws Exception {
       // final String id = "con1";
       // given(createEventControllerMock.getEvent(id)).willReturn(id);
        // mockMvc.perform(getEvent(EventController.URL, id).accept(MediaType.APPLICATION_JSON_UTF8).
          //      andExcept(status().isOk()).andExpect(content().string(id)));

    }
    @Test
    public void createEvent() throws Exception {
        final Event event = new Event("Con1");
     //   given(createEventControllerMock.createEvent(event)).willReturn(new Event(event));
       // mockMvc.perform(post("/events")
          //      .contentType(MediaType.APPLICATION_JSON)
           //     .content(objectMapper.writeValueAsBytes(new CreateEventRequest(event))))
         //      .andExpect(status().isCreated())
           //     .andExpect(jsonPath("$.event", is(event)))
            //    .andExpect(jsonPath("$.id", notNullValue()));

   // }

    //@Test
    //public void updateEvent() throws Exception {

   // }

   // @Test
   // public void deleteEvent() throws Exception {

    //}

}*/