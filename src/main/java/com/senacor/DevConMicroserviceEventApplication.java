package com.senacor;

import com.senacor.model.Event;
import com.senacor.service.EventService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;





@SpringBootApplication
public class DevConMicroserviceEventApplication implements CommandLineRunner{

    @Autowired
    EventService eventService;

	public static void main(String[] args) {

		SpringApplication.run(DevConMicroserviceEventApplication.class, args);
	}

    @Override
    public void run(String... strings) throws Exception {
/*        eventService.deleteAllEvents();

        Event event = new Event();
        event.setDate(new LocalDate(2016, 12, 24));
        event.setName("Datum Serialisieren");
        eventService.addEvent(event);*/
    }
}
