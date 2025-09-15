package com.ninabornemann.spring_data_asterixapi.service;

import com.ninabornemann.spring_data_asterixapi.model.Characters;
import com.ninabornemann.spring_data_asterixapi.repository.CharacterRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AsterixService {

    private final CharacterRepo repo;

    public AsterixService(CharacterRepo repo) {
        this.repo = repo;
    }

    public List<Characters> getCharacters() {
        return repo.findAll();
    }

    public List<Characters> getCharacterByProperty(Optional<String> namePrefix) {
        log.info("our namePrefix is: " + namePrefix);
        List<Characters> characters = new ArrayList<>();
        List<Characters> all = repo.findAll();
        for (Characters c : all) {
            if (namePrefix.isPresent() && c.getName().startsWith(namePrefix.get())) {
                characters.add(c);
            }
        }
        return characters;
    }

    public Characters addCharacter(Characters value) {
        return repo.insert(value);
    }

    public Characters updateCharacter(Characters value) {
        if (value.getId() == null || value.getId().isEmpty()) {
            throw new IllegalArgumentException("you must set an Id.");
        }
        return repo.save(value);
    }

    public void deleteCharacterById(String id) {
        repo.deleteById(id);
    }
}
