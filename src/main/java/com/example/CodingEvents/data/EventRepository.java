package com.example.CodingEvents.data;

import com.example.CodingEvents.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository // (one of the features) spring creates instance of this object for dependency injection
public interface EventRepository extends CrudRepository<Event, Integer> {

}
