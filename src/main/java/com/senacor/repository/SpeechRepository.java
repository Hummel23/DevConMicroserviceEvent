package com.senacor.repository;

import com.senacor.model.Speech;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by saba on 31.10.16.
 */

@Repository
@RepositoryRestResource(collectionResourceRel = "event", path = "speech")
public interface SpeechRepository extends MongoRepository<Speech, String> {

    List<Speech> findByEventID(@Param("eventID") String eventID);
}
