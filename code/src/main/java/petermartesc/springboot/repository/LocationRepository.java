package petermartesc.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import petermartesc.springboot.model.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}
