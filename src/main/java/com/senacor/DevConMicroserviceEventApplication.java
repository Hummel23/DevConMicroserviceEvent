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

        eventService.deleteAllEvents();
        for (int i = 1; i <=1; i++){
            Event event = new Event();
            event.setName("Conference No." + i);
            event.setPlace("Example Street No. " + i);

            LocalDate date = new LocalDate(2016, 9, i);
            DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy");
            String str = date.toString(fmt);
            event.setDate(new LocalDate(2016, 9, i).toString(str));

            for (int j = 1; j < 5; j++) {

                Speech speech = new Speech(event.getEventId());

                speech.setSpeaker("speaker: " + j);
                speech.setStartTime(LocalTime.of(j, 00));
                speech.setEndTime(LocalTime.of(j+1, 30));
                event.getSpeeches().add(speech);
            }

            Event stored = eventService.createEvent(event);
            System.out.println(stored.getEventId());
            System.out.println(stored.getDate());

        }

/*        for (Event event : eventService.listAllEvents()) {
            System.out.println(event.getName());
           // System.out.println(event.getDate());
            System.out.println(event.getPlace());
            System.out.println(event.getEventId());
            for (Speech speech: eventService.getAllSpeechesForEvent(event.getEventId())) {
                System.out.println(speech.getSpeaker());
                System.out.println(speech.getStartTime());
                System.out.println(speech.getEndTime());
                System.out.println(speech.getSpeechId());
            }

        }*/

    }
}
