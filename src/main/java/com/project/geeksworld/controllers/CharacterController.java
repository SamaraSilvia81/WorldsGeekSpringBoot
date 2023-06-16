package com.project.geeksworld.controllers;

import com.project.geeksworld.models.CharacterEntity;
import com.project.geeksworld.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/character")
public class CharacterController {

    @Autowired
    CharacterRepository characterRepository;

    @GetMapping
    public List<CharacterEntity> list() {
        return characterRepository.findAll();
    }
    
    @GetMapping("/{characterId}")
    public CharacterEntity getUser(@PathVariable Long characterId) {
        return characterRepository.findById(characterId).orElse(null);
    }

    @PostMapping
    public CharacterEntity create(@RequestBody CharacterEntity character) {
        return characterRepository.save(character);
    }

    @PutMapping("/{characterId}")
    public String updatedCharacter(@PathVariable Long characterId, @RequestBody CharacterEntity updatedCharacter) {
        CharacterEntity character = characterRepository.findById(characterId).orElse(null);

        if (character != null) {

            // Atualizar os campos necessários
            character.setCharname(updatedCharacter.getCharname());
            character.setPhoto(updatedCharacter.getPhoto());
            character.setAbout(updatedCharacter.getAbout());
            character.setIsMarvel(updatedCharacter.getIsMarvel());
            character.setIsHero(updatedCharacter.getIsHero());

            characterRepository.save(character);
            return "CHARACTER UPDATED";
        } else {
            return "User not found";
        }
    }

    @DeleteMapping("/{characterId}")
    public String deleteCharacter(@PathVariable Long characterId) {
        boolean exists = characterRepository.existsById(characterId);
        if (exists) {
            characterRepository.deleteById(characterId);
            return "CHARACTER DELETED";
        } else {
            return "Character not found";
        }
    }
}
