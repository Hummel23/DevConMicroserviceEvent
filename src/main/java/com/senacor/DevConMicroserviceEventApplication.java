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
        for (int i = 1; i <=6; i++){
            Event event = new Event();
            event.setName("Conference No." + i);
            event.setPlace("Example Street No. " + i);

            LocalDate date = new LocalDate(2016, 9, i);
            DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy");
            String str = date.toString(fmt);
            event.setDate(new LocalDate(2016, 9, i));

            for (int j = 1; j < 5; j++) {

                Speech speech = new Speech(event.getEventId());

                speech.setSpeaker("speaker: " + j);
                speech.setSpeechRoom("Room " + j + "00");
                speech.setSpeechTitle( j + " million reasons to program");


                //DateTimeFormatter format = DateTimeFormat.forPattern("HH:mm");


                speech.setStartTime(LocalTime.of(j, 00).toString());
                speech.setEndTime(LocalTime.of(j+1, 30).toString());



                event.getSpeeches().add(speech);
               // System.out.println("" + speech.getStartTime());
               // System.out.println("" + speech.getEndTime());
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
