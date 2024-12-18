package es.ies.puerto.petermartesc.springboot.repository;

import es.ies.puerto.petermartesc.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
