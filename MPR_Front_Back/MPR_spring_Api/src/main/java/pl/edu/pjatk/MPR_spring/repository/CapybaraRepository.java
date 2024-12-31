package pl.edu.pjatk.MPR_spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.MPR_spring.model.Capybara;

import java.util.List;

@Repository
public interface CapybaraRepository extends CrudRepository<Capybara, Long> {
    List<Capybara> findByName(String name);
    List<Capybara> findByColor(String color);
    boolean existsByNameAndColor(String name, String color);
}
