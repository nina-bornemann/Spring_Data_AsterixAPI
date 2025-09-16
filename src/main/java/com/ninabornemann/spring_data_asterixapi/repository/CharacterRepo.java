package com.ninabornemann.spring_data_asterixapi.repository;

import com.ninabornemann.spring_data_asterixapi.model.Characters;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepo extends MongoRepository<Characters, String> {

    List<Characters> id(String id);
}
