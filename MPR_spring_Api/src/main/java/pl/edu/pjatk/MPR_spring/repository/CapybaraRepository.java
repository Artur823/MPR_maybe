package pl.edu.pjatk.MPR_spring.repository;

import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;
import pl.edu.pjatk.MPR_spring.model.Capybara;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface CapybaraRepository extends CrudRepository<Capybara, Long> {
    public List<Capybara> findByName(String name);
    List<Capybara> findByColor(String color);
    void deleteById(Integer id);
    boolean existsCarByIdentification(long identification);

    boolean existsByName(String name);
}
