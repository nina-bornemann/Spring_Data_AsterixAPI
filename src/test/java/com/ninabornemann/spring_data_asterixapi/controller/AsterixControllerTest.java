package com.ninabornemann.spring_data_asterixapi.controller;

import com.ninabornemann.spring_data_asterixapi.model.Characters;
import com.ninabornemann.spring_data_asterixapi.repository.CharacterRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AsterixControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CharacterRepo repo;

    @Test
    @DirtiesContext
    void getAllCharacters() throws Exception {
        Characters c1 = new Characters("12345CC", "Cupcake", 25, "Scientist");
        repo.save(c1);

        mockMvc.perform(MockMvcRequestBuilders.get("/asterix/characters"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                        [
                            {
                                "id": "12345CC",
                                "name": "Cupcake",
                                "age": 25,
                                "profession": "Scientist"
                            }
                        ]
                        """
                ));
    }

    @Test
    @DirtiesContext
    void getCharactersByName() throws Exception {
        Characters c1 = new Characters("12345CC", "Cupcake", 25, "Scientist");
        Characters c2 = new Characters("12345PC", "Pancake", 29, "Hacker");
        repo.save(c1);
        repo.save(c2);

        mockMvc.perform(MockMvcRequestBuilders.get("/asterix/characters/byName?name=Pancake"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                        [
                            {
                              "id": "12345PC",
                              "name": "Pancake",
                              "age": 29,
                              "profession": "Hacker"
                            }
                        ]
                        """
                ));
    }

    @Test
    @DirtiesContext
    void getCharacterById() throws Exception {
        Characters c1 = new Characters("12345CC", "Cupcake", 25, "Scientist");
        Characters c2 = new Characters("12345PC", "Pancake", 29, "Hacker");
        repo.save(c1);
        repo.save(c2);

        mockMvc.perform(MockMvcRequestBuilders.get("/asterix/characters/12345CC"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                            {
                              "id": "12345CC",
                              "name": "Cupcake",
                              "age": 25,
                              "profession": "Scientist"
                            }
                        """
                ));
    }

    @Test
    @DirtiesContext
    void getCharacterByAge_exactAge() throws Exception {
        Characters c1 = new Characters("12345CC", "Cupcake", 25, "Scientist");
        Characters c2 = new Characters("12345PC", "Pancake", 29, "Hacker");
        repo.save(c1);
        repo.save(c2);

        mockMvc.perform(MockMvcRequestBuilders.get("/asterix/characters/byAge?exactAge=29"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                        [
                            {
                              "id": "12345PC",
                              "name": "Pancake",
                              "age": 29,
                              "profession": "Hacker"
                            }
                        ]
                        """
                ));
    }

    @Test
    @DirtiesContext
    void getCharacterByAge_minAndMaxAge() throws Exception {
        Characters c1 = new Characters("12345CC", "Cupcake", 25, "Scientist");
        Characters c2 = new Characters("12345PC", "Pancake", 29, "Hacker");
        Characters c3 = new Characters("12345LC", "Lemoncake", 35, "Baker");
        repo.save(c1);
        repo.save(c2);
        repo.save(c3);

        mockMvc.perform(MockMvcRequestBuilders.get("/asterix/characters/byAge?minAge=28&&maxAge=36"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                        [
                            {
                              "id": "12345PC",
                              "name": "Pancake",
                              "age": 29,
                              "profession": "Hacker"
                            },
                            {
                              "id": "12345LC",
                              "name": "Lemoncake",
                              "age": 35,
                              "profession": "Baker"
                            }
                        ]
                        """
                ));
    }

    @Test
    @DirtiesContext
    void addCharacter() throws Exception {
       mockMvc.perform(MockMvcRequestBuilders.post("/asterix/characters")
               .contentType(MediaType.APPLICATION_JSON).content(
                       """
                           {
                            "name": "Pancake",
                            "age": 29,
                            "profession": "Hacker"
                           }
                       """
               ))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().json(
                       """
                            {
                                "name": "Pancake",
                                "age": 29,
                                "profession": "Hacker"
                            }
                       """
               ))
               .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
    }
}