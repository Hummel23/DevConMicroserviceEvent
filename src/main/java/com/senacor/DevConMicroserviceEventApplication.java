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

       eventService.deleteAllEvents();

    }
}
