package geeksworld.project.repositories;

import geeksworld.project.models.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository <CharacterEntity, Long> {
}
