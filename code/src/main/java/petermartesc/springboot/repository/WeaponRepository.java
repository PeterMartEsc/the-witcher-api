package petermartesc.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import petermartesc.springboot.model.Weapon;

public interface WeaponRepository extends JpaRepository<Weapon, Integer> {
}
