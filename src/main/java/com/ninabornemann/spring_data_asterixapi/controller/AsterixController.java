package com.ninabornemann.spring_data_asterixapi.controller;

import com.ninabornemann.spring_data_asterixapi.repository.CharacterRepo;
import com.ninabornemann.spring_data_asterixapi.Characters;
import com.ninabornemann.spring_data_asterixapi.service.AsterixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/asterix/characters")
public class AsterixController {

    private final AsterixService service;

    public AsterixController(CharacterRepo repo, AsterixService service) {
        this.service = service;
    }

    @GetMapping
    public List<Characters> getCharacters() {
        return service.getCharacters();
    }

    @GetMapping("/byProperty")
    public List<Characters> getCharacterByProperty(@RequestParam Optional<String> namePrefix) {
        return service.getCharacterByProperty(namePrefix);
    }

    @PostMapping
    public Characters addCharacter(@RequestBody Characters value) {
        return service.addCharacter(value);
    }

    @PutMapping
    public Characters updateCharacter(@RequestBody Characters value) {
        return service.updateCharacter(value);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacterById(@PathVariable String id) {
        service.deleteCharacterById(id);
    }
}
