package com.senacor.repository;

import com.senacor.model.Event;
import com.senacor.model.Speech;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Nadia on 23.10.2016.
 */

@Repository
@RepositoryRestResource(collectionResourceRel = "event", path = "event")
public interface EventRepository extends MongoRepository<Event, String> {

    //Event findByEventId(@Param("eventId") String eventId);

}
