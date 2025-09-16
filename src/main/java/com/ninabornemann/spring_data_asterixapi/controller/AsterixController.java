package com.ninabornemann.spring_data_asterixapi.controller;

import com.ninabornemann.spring_data_asterixapi.dto.CharacterDto;
import com.ninabornemann.spring_data_asterixapi.repository.CharacterRepo;
import com.ninabornemann.spring_data_asterixapi.model.Characters;
import com.ninabornemann.spring_data_asterixapi.service.AsterixService;
import com.ninabornemann.spring_data_asterixapi.service.IdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/asterix/characters")
public class AsterixController {

    private final AsterixService service;
    private final IdService idService;

    public AsterixController(CharacterRepo repo, AsterixService service, IdService idService) {
        this.service = service;
        this.idService = idService;
    }

    @GetMapping
    public List<Characters> getCharacters() {
        return service.getCharacters();
    }

    @GetMapping("/byProperty")
    public List<Characters> getCharacterByProperty(@RequestParam Optional<String> namePrefix) {
        return service.getCharacterByProperty(namePrefix);
    }

    @GetMapping("/{id}")
    public Characters getCharacterById(@PathVariable String id) {
        return service.getCharacterById(id);
    }

    @PostMapping
    public Characters addCharacter(@RequestBody CharacterDto value) {
        return service.addCharacter(value);
    }

    @PutMapping("/{id}")
    public Characters updateCharacterById(@PathVariable String id, @RequestBody CharacterDto value) {
        return service.updateCharacterById(id, value);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacterById(@PathVariable String id) {
        service.deleteCharacterById(id);
    }
}
