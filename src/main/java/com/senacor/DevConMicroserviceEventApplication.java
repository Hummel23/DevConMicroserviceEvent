package com.senacor;

import com.senacor.model.Event;
import com.senacor.model.Speech;
import com.senacor.repository.EventRepository;
import com.senacor.service.EventService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalTime;
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

        //eventRepository.deleteAll();


   /*     for (Event event :
                eventRepository.findAll()) {
            if(! event.getDate().isAfter(LocalDate.now())){
                eventRepository.delete(event);
            }
        }
        Event event = new Event();
        event.setDate(new LocalDate(2015, 03, 02));
        event.setPlace("Berlin");
        event.setName("Event in der Vergangenheit");
        eventRepository.save(event);

        Event event1 = new Event();
        event1.setDate(LocalDate.now());
        event1.setPlace("Berlin");
        event1.setName("heutiges");
        eventRepository.save(event1);

        Event oldestEvent = eventRepository.findAllByOrderByDateDesc().get(eventRepository.findAll().size()-1);

        Speech speech = new Speech();
        speech.setSpeechTitle("past speeches cannot be amended");
        speech.setStartTime(LocalTime.of(14, 00));
        speech.setEndTime(LocalTime.of(15, 30));

        oldestEvent.getSpeeches().add(speech);
        eventRepository.save(oldestEvent);*/
    }
}
