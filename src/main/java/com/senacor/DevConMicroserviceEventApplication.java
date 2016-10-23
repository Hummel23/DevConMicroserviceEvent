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
        eventRepository.save(new Event("conference"));
        eventRepository.save(new Event("conference1"));
        eventRepository.save(new Event("conference2"));
        eventRepository.save(new Event("conference3"));

        for (Event event : eventRepository.findAll()) {
            System.out.println(event.getName());
        }

    }
}
