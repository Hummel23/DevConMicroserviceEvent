package com.senacor;

import com.senacor.model.Event;
import com.senacor.repository.EventRepository;
import com.senacor.service.EventService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class DevConMicroserviceEventApplication implements CommandLineRunner{

    @Autowired
    EventService eventService;

    @Autowired
    EventRepository eventRepository;

	public static void main(String[] args) {

		SpringApplication.run(DevConMicroserviceEventApplication.class, args);
	}

    @Override
    public void run(String... strings) throws Exception {
/*
       eventService.deleteAllEvents();

        Event event = new Event();
        event.setDate(new LocalDate(2016, 12, 24));
        event.setName("mittleres DAtum");
        eventService.addEvent(event);

        Event event1 = new Event();
        event1.setDate(new LocalDate(2017, 02, 8));
        event1.setName("jüngstes datum");
        eventService.addEvent(event1);

        Event event2 = new Event();
        event2.setDate(new LocalDate(2015, 02, 8));
        event2.setName("ältestes Datum");
        eventService.addEvent(event2);

        List<Event> events = eventRepository.findAllByOrderByDateDesc();

        for (Event e : events) {
            System.out.println(e.getName());
        }
*/


    }
}
