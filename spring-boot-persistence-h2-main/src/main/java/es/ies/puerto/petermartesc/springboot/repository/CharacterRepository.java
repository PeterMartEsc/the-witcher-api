package es.ies.puerto.petermartesc.springboot.repository;

import es.ies.puerto.petermartesc.springboot.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Integer> {
}
