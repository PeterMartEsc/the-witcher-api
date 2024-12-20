package petermartesc.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import petermartesc.springboot.model.Alchemy;

public interface AlchemyRepository extends JpaRepository<Alchemy, Integer> {
}
