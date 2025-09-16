package com.ninabornemann.spring_data_asterixapi.service;

import com.ninabornemann.spring_data_asterixapi.dto.CharacterDto;
import com.ninabornemann.spring_data_asterixapi.model.Characters;
import com.ninabornemann.spring_data_asterixapi.repository.CharacterRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AsterixService {

    private final CharacterRepo repo;
    private final IdService idService;

    public AsterixService(CharacterRepo repo, IdService idService) {
        this.repo = repo;
        this.idService = idService;
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

    public Characters addCharacter(CharacterDto value) {
        Characters c = new Characters(idService.randomId(), value.getName(), value.getAge(), value.getProfession());
        return repo.insert(c);
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

    public Characters getCharacterById(String id) {
        return repo.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Characters updateCharacterById(String id, Characters value) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Character with id " + id + " not found");
        }
        value.setId(id);
        return updateCharacter(value);
    }
}
