package com.senacor;

import com.senacor.model.Event;
import com.senacor.model.Speech;
import com.senacor.service.EventService;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.time.LocalTime;


@SpringBootApplication
public class DevConMicroserviceEventApplication implements CommandLineRunner{

    @Autowired
    EventService eventService;

	public static void main(String[] args) {

		SpringApplication.run(DevConMicroserviceEventApplication.class, args);
	}

    @Override
    public void run(String... strings) throws Exception {

        for (int i = 1; i <=6; i++){
            Event event = new Event();
            event.setName("Conference No." + i);
            event.setPlace("Example Street No. " + i);

            LocalDate date = new LocalDate(2016, 9, i);
            DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy");
            String str = date.toString(fmt);
            event.setDate(new LocalDate(2016, 9, i));

            Event stored = eventService.createEvent(event);
            System.out.println(stored.getEventId());
            System.out.println(stored.getDate());

        }


    }
}
