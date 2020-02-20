package com.example.CodingEvents.data;

import com.example.CodingEvents.models.EventCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventCategoriesRepository extends CrudRepository<EventCategory, Integer> {

}
