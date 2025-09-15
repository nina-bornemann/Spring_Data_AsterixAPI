package com.ninabornemann.spring_data_asterixapi.repository;

import com.ninabornemann.spring_data_asterixapi.model.Characters;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepo extends MongoRepository<Characters, String> {

}
