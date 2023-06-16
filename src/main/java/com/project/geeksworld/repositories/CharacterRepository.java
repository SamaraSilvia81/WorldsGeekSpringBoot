package com.project.geeksworld.repositories;

import com.project.geeksworld.models.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository <CharacterEntity, Long> {
}
