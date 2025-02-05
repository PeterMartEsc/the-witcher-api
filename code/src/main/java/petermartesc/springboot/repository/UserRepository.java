package petermartesc.springboot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import petermartesc.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    @Query(
            value="SELECT * FROM users WHERE name = :nombre",
            nativeQuery = true
    ) //Native Query
    Optional<User> findByNombre(@Param("nombre") String nombre);

}
