package petermartesc.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import petermartesc.springboot.model.Monster;

public interface MonsterRepository extends JpaRepository<Monster, Integer> {
}
