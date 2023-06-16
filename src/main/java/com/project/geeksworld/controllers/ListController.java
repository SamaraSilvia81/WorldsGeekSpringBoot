package com.project.geeksworld.controllers;

import com.project.geeksworld.models.CharacterEntity;
import com.project.geeksworld.models.ListEntity;
import com.project.geeksworld.models.UserEntity;

import com.project.geeksworld.repositories.CharacterRepository;
import com.project.geeksworld.repositories.ListRepository;
import com.project.geeksworld.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/list")
public class ListController {

    private final ListRepository listRepository;
    private final UserRepository userRepository;
    private final CharacterRepository characterRepository;

    public ListController(ListRepository listRepository, UserRepository userRepository, CharacterRepository characterRepository) {
        this.listRepository = listRepository;
        this.userRepository = userRepository;
        this.characterRepository = characterRepository;
    }

    @GetMapping
    public java.util.List<ListEntity> list() {
        return listRepository.findAll();
    }

    @PostMapping
    public String createList(@RequestBody ListEntity listEntity) {
        // Verificar se o usuário existe
        UserEntity user = userRepository.findById(listEntity.getUser().getUserId()).orElse(null);
        if (user == null) {
            return "User not found";
        }

        // Verificar se os personagens existem
        List<CharacterEntity> characters = new ArrayList<>();
        for (CharacterEntity character : listEntity.getCharacters()) {
            CharacterEntity existingCharacter = characterRepository.findById(character.getCharacterId()).orElse(null);
            if (existingCharacter != null) {
                characters.add(existingCharacter);
            }
        }

        // Criar a lista e salvar no repositório
        listEntity.setUser(user);
        listEntity.setCharacters(characters);
        listRepository.save(listEntity);

        return "List created";
    }

    @PutMapping("/{listId}")
    public String updatedList(@PathVariable Long listId, @RequestBody ListEntity updatedList) {
        ListEntity list = listRepository.findById(listId).orElse(null);

        if (list != null) {
            // Atualizar os campos necessários
            list.setListname(updatedList.getListname());
            list.setCharacters(updatedList.getCharacters());
            list.setUser(updatedList.getUser());

            listRepository.save(list);
            return "LIST UPDATED";
        } else {
            return "List not found";
        }
    }

    @DeleteMapping("/{listId}")
    public String deleteList(@PathVariable Long listId) {
        boolean exists = listRepository.existsById(listId);
        if (exists) {
            listRepository.deleteById(listId);
            return "LIST DELETED";
        } else {
            return "List not found";
        }
    }
}
