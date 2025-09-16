package com.ninabornemann.spring_data_asterixapi.controller;

import com.ninabornemann.spring_data_asterixapi.model.Characters;
import com.ninabornemann.spring_data_asterixapi.repository.CharacterRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
                        ]"""
                ));
    }

}