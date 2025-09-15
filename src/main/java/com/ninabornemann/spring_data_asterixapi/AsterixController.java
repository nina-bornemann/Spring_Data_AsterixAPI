package com.ninabornemann.spring_data_asterixapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/asterix/characters")
public class AsterixController {

    private final CharacterRepo repo;

    public AsterixController(CharacterRepo repo) {
        this.repo = repo;
    }


    @GetMapping
    public List<Characters> getCharacters() {
        return repo.findAll();
    }

    @GetMapping("/byProperty")
    public List<Characters> getCharacterByProperty(@RequestParam Optional<String> namePrefix) {
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

    @PostMapping
    public Characters addCharacter(@RequestBody Characters value) {
        return repo.insert(value);
    }

    @PutMapping
    public Characters updateCharacter(@RequestBody Characters value) {
        if (value.getId() == null || value.getId().isEmpty()) {
            throw new IllegalArgumentException("you must set an Id.");
        }
        return repo.save(value);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacterById(@PathVariable String id) {
        repo.deleteById(id);
    }
}
