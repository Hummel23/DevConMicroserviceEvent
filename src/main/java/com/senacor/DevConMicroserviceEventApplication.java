package com.senacor;

import com.senacor.model.Event;
import com.senacor.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class DevConMicroserviceEventApplication implements CommandLineRunner{

    @Autowired
    EventRepository eventRepository;

	public static void main(String[] args) {

		SpringApplication.run(DevConMicroserviceEventApplication.class, args);
	}

    @Override
    public void run(String... strings) throws Exception {
        eventRepository.deleteAll();
        for (int i = 1; i <= 10; i++){
            Event event = new Event();
            event.setName("Conference No." + i);
            event.setPlace("Example Street No. " + i);
            eventRepository.save(event);
        }

        for (Event event : eventRepository.findAll()) {
            System.out.println(event.getName());
        }

    }
}
