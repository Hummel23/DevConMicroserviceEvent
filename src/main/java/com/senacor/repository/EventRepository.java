package com.senacor.repository;

import com.senacor.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Nadia on 23.10.2016.
 */
@RepositoryRestResource(collectionResourceRel = "event", path = "event")
public interface EventRepository extends MongoRepository<Event, String> {
}
