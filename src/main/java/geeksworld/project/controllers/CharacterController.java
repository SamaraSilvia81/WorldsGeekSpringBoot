package geeksworld.project.controllers;

import geeksworld.project.models.CharacterEntity;
import geeksworld.project.repositories.CharacterRepository;
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

    @PostMapping
    public CharacterEntity create(@RequestBody CharacterEntity character) {
        return characterRepository.save(character);
    }

    @PutMapping("/{characterId}")
    public ResponseEntity<CharacterEntity> update(@PathVariable Long characterId, @RequestBody CharacterEntity updatedCharacter) {
        Optional<CharacterEntity> existingCharacterOptional = characterRepository.findById(characterId);
        if (existingCharacterOptional.isPresent()) {
            CharacterEntity existingCharacter = existingCharacterOptional.get();
            existingCharacter.setName(updatedCharacter.getName());
            existingCharacter.setPhoto(updatedCharacter.getPhoto());
            existingCharacter.setAbout(updatedCharacter.getAbout());
            characterRepository.save(existingCharacter);
            return ResponseEntity.ok(existingCharacter);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{characterId}")
    public ResponseEntity<Void> delete(@PathVariable Long characterId) {
        Optional<CharacterEntity> characterOptional = characterRepository.findById(characterId);
        if (characterOptional.isPresent()) {
            characterRepository.delete(characterOptional.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

