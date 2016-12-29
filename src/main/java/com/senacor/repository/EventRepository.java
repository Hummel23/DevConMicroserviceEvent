package com.senacor.repository;

import com.senacor.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Nadia on 23.10.2016.
 */

@Repository
public interface EventRepository extends MongoRepository<Event, String> {

    Event findByEventId(String eventId);
    List<Event> findAllByOrderByDateDesc();
}
