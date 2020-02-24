package com.example.CodingEvents.data;


import com.example.CodingEvents.models.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagReporsitory extends CrudRepository<Tag, Integer> {
}
