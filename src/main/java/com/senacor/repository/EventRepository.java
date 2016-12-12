package com.senacor.repository;

import com.senacor.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Nadia on 23.10.2016.
 */

@Repository
//@RepositoryRestResource(collectionResourceRel = "event", path = "event")
public interface EventRepository extends MongoRepository<Event, String> {

    Event findByEventId(String eventId);
    List<Event> findAllOrderByDate(); //Comparable Interface wieder rausnehmen Event-Klasse

    //find all / orderBy nachschlagen - in Mongo direkt sortieren
    //Event Repository implement Klasse

}
