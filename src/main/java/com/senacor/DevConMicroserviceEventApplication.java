package com.senacor;

import com.senacor.model.Event;
import com.senacor.model.Speech;
import com.senacor.repository.EventRepository;
import com.senacor.repository.SpeechRepository;
import org.joda.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalTime;

@SpringBootApplication
public class DevConMicroserviceEventApplication implements CommandLineRunner{

    @Autowired
    EventRepository eventRepository;

    @Autowired
    SpeechRepository speechRepository;

	public static void main(String[] args) {

		SpringApplication.run(DevConMicroserviceEventApplication.class, args);
	}

    @Override
    public void run(String... strings) throws Exception {

        eventRepository.deleteAll();
        speechRepository.deleteAll();
        for (int i = 1; i <= 10; i++){
            Event event = new Event();
            event.setName("Conference No." + i);
            event.setPlace("Example Street No. " + i);
            event.setDate(new LocalDate(2016, 9, i));
            eventRepository.save(event);



            for (int j = 0; j < 2; j++) {
                Speech speech = new Speech(event.getId());
                speech.setSpeaker("Speaker " + j);

                speech.setStartTime(LocalTime.of(j, 00));

                speech.setEndTime(LocalTime.of(j+1, 30));
                speechRepository.save(speech);
            }

        }

        for (Event event : eventRepository.findAll()) {
            System.out.println(event.getName());
            System.out.println(event.getDate());
            System.out.println(event.getPlace());
            System.out.println(event.getId());
            for (Speech speech: speechRepository.findByEventID(event.getId())) {
                System.out.println(speech.getSpeaker());
                System.out.println(speech.getStartTime());
                System.out.println(speech.getEndTime());
            }

        }

    }
}
