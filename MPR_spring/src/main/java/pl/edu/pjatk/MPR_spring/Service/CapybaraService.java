package pl.edu.pjatk.MPR_spring.Service;

import org.apache.catalina.valves.rewrite.InternalRewriteMap;

import org.springframework.stereotype.Component;
import pl.edu.pjatk.MPR_spring.model.Capybara;
import pl.edu.pjatk.MPR_spring.repository.CapybaraRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CapybaraService {
    private List<Capybara> capybaraList = new ArrayList<>();
    private StringUtilsService stringUtilsService ;
    private CapybaraRepository repository ;

    public CapybaraService(CapybaraRepository repository, StringUtilsService stringUtilsService) {
        this.repository = repository;
        this.stringUtilsService = stringUtilsService;
        this.repository.save(new Capybara("JAkub", "brown"));
        this.repository.save(new Capybara("MAks", "green"));
        this.repository.save(new Capybara("andrey", "black"));
    }
    public List<Capybara> getCapybaraByName(String name) {
        return repository.findByName(name);
    }
    public List<Capybara> getCapybaraByColor(String color) {
        return repository.findByColor(color);
    }



    public Iterable<Capybara> getCapybaraList() {

        Iterable<Capybara> all = repository.findAll();
        for (Capybara capybara : all) {

        }
        return all;
    }

    public Capybara saveCapybara(Capybara capybara) {

        capybara.setName(this.stringUtilsService.UpperCase(capybara.getName()));
        capybara.setColor(this.stringUtilsService.UpperCase(capybara.getColor()));
        return repository.save(capybara);
    }

    public Optional<Capybara> getCapybara(Long id) {
        return this.repository.findById(id);
    }

    public void delete(Integer id) {
        this.repository.deleteById(id);
    }


    public void update(String name, String color, Capybara newCapybara) {
        repository.findByName(name).stream().filter
                (c -> c.getColor().equals(color)).forEach(existing -> { existing.setName(newCapybara.getName());
            existing.setColor(newCapybara.getColor()); repository.save(existing);});
    }



}

