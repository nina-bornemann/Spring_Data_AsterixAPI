package com.ninabornemann.spring_data_asterixapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/asterix/characters")
public class AsterixController {

    @GetMapping
    public List<Characters> getCharacters() {
        return List.of(
                new Characters("1", "Asterix", 35, "Warrior"),
                new Characters("2", "Obelix", 35, "Supplier"),
                new Characters("3", "Miraculix", 60, "Druid"),
                new Characters("4", "Majestix", 60, "Chief"),
                new Characters("5", "Troubadix", 25, "Bard"),
                new Characters("6", "Gutemine", 35, "Chiefs Wife"),
                new Characters("7", "Idefix", 5, "Dog"),
                new Characters("8", "Geriatrix", 70, "Retiree"),
                new Characters("9", "Automatix", 35, "Smith"),
                new Characters("10", "Grockelix", 35, "Fisherman")
        );
    }
}
