package com.ninabornemann.spring_data_asterixapi.service;

import com.ninabornemann.spring_data_asterixapi.dto.CharacterDto;
import com.ninabornemann.spring_data_asterixapi.model.Characters;
import com.ninabornemann.spring_data_asterixapi.repository.CharacterRepo;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AsterixServiceTest {

    @Test
    void getCharacters() {
        CharacterRepo mockRepo = mock(CharacterRepo.class);
        IdService mockIdService = mock(IdService.class);
        AsterixService service = new AsterixService(mockRepo, mockIdService);
        Characters c1 = new Characters("001", "Pancake", 29, "Hacker");
        Characters c2 = new Characters("002", "Cupcake", 25, "Scientist");
        List<Characters> response = List.of(c1, c2);
        when(mockRepo.findAll()).thenReturn(response);

        List<Characters> actual = service.getCharacters();

        assertEquals(response, actual);
        verify(mockRepo).findAll();
    }

    @Test
    void deleteCharacterById() {
        CharacterRepo mockRepo = mock(CharacterRepo.class);
        IdService mockIdService = mock(IdService.class);
        AsterixService service = new AsterixService(mockRepo, mockIdService);
        Characters c1 = new Characters("001", "Pancake", 29, "Hacker");
        Characters c2 = new Characters("002", "Cupcake", 25, "Scientist");
        List<Characters> charactersList = List.of(c1, c2);
        when(mockRepo.existsById("001")).thenReturn(false);
        boolean actual = service.deleteCharacterById("001");
        assertFalse(actual);
        verify(mockRepo).deleteById("001");
    }

    @Test
    void getCharacterById() {
        CharacterRepo mockRepo = mock(CharacterRepo.class);
        IdService mockIdService = mock(IdService.class);
        AsterixService service = new AsterixService(mockRepo, mockIdService);
        Characters c1 = new Characters("001", "Pancake", 29, "Hacker");
        Characters c2 = new Characters("002", "Cupcake", 25, "Scientist");
        List<Characters> charactersList = List.of(c1, c2);
        Optional<Characters> response = Optional.of(c1);
        when(mockRepo.findById("001")).thenReturn(response);
        Characters actual = service.getCharacterById("001");
        assertEquals(c1, actual);
        verify(mockRepo).findById("001");
    }

    @Test
    void updateCharacterById() {
        CharacterRepo mockRepo = mock(CharacterRepo.class);
        IdService mockIdService = mock(IdService.class);
        AsterixService service = new AsterixService(mockRepo, mockIdService);

        Characters existing = new Characters("001", "Pancake", 29, "Hacker");
        CharacterDto updated = new CharacterDto("Cupcake", 25, "Scientist");

        when(mockRepo.findById("001")).thenReturn(Optional.of(existing));
        when(mockRepo.save(any(Characters.class))).thenAnswer(i -> i.getArgument(0));

        Characters updatedCharacter = service.updateCharacterById("001", updated);

        assertEquals("Cupcake", updatedCharacter.getName());
        assertEquals(25, updatedCharacter.getAge());
        assertEquals("Scientist", updatedCharacter.getProfession());
        assertEquals("001", updatedCharacter.getId());
        verify(mockRepo).findById("001");
        verify(mockRepo).save(existing);
    }
}