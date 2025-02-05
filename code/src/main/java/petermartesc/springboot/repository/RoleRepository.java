package petermartesc.springboot.repository;

import org.springframework.stereotype.Repository;
import petermartesc.springboot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
