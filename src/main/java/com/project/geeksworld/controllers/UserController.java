package com.project.geeksworld.controllers;

import com.project.geeksworld.models.UserEntity;
import com.project.geeksworld.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*; // Importar a classe List corretamente

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
    UserRepository userRepository;

    @GetMapping("/{userId}")
    public UserEntity getUser(@PathVariable Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
    
    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
    
    @PostMapping
    public UserEntity signIn (@RequestBody UserEntity user){
        return userRepository.save(user);
    }

    @PutMapping("/{userId}")
    public String updateUser(@PathVariable Long userId, @RequestBody UserEntity updatedUser) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        if (user != null) {

            // Atualizar os campos necessários
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());

            userRepository.save(user);
            return "USER UPDATED";
        } else {
            return "User not found";
        }
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (exists) {
            userRepository.deleteById(userId);
            return "USER DELETED";
        } else {
            return "User not found";
        }
    }

}
