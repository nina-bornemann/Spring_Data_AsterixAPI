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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Characters> getCharacterByName(Optional<String> name) {
        log.info("our namePrefix is: " + name);
        List<Characters> characters = new ArrayList<>();
        List<Characters> all = repo.findAll();
        for (Characters c : all) {
            if (name.isPresent() && c.getName().startsWith(name.get())) {
                characters.add(c);
            }
        }
        return characters;
    }

    public List<Characters> getCharacterByAge(Integer exactAge, Integer minAge, Integer maxAge) {
        List<Characters> characters = new ArrayList<>();
        List<Characters> all = repo.findAll();
        return all.stream()
                .filter(c -> exactAge == null || c.getAge() == exactAge)
                .filter(c -> minAge == null || c.getAge() >= minAge)
                .filter(c -> maxAge == null || c.getAge() <= maxAge)
                .collect(Collectors.toList());
    }

    public Characters addCharacter(CharacterDto value) {
        Characters c = new Characters(idService.randomId(), value.getName(), value.getAge(), value.getProfession());
        return repo.insert(c);
    }

    public boolean deleteCharacterById(String id) {
        repo.deleteById(id);
        return repo.existsById(id);
    }

    public Characters getCharacterById(String id) {
        return repo.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Characters updateCharacterById(String id, CharacterDto value) {
        Characters existing = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Character with id " + id + " not found"));
        existing.setName(value.getName());
        existing.setAge(value.getAge());
        existing.setProfession(value.getProfession());
        return repo.save(existing);
    }

    public Integer getAverageAgeByProfession(String profession) {
        List<Characters> all = repo.findAll();
        List<Characters> professionList = all.stream()
                .filter(c -> Objects.equals(c.getProfession(), profession))
                .toList();
        int sum = professionList.stream()
                .map(Characters::getAge)
                .reduce(0, Integer::sum);
        return sum / professionList.size();
    }
}
