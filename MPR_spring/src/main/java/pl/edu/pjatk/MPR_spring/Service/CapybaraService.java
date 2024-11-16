package pl.edu.pjatk.MPR_spring.Service;

import org.apache.catalina.valves.rewrite.InternalRewriteMap;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.edu.pjatk.MPR_spring.exception.CapybaraAlredyExist;
import pl.edu.pjatk.MPR_spring.exception.CapybaraNotFoundException;
import pl.edu.pjatk.MPR_spring.model.Capybara;
import pl.edu.pjatk.MPR_spring.repository.CapybaraRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CapybaraService {
    private List<Capybara> capybaraList = new ArrayList<>();
    private StringUtilsService stringUtilsService ;
    private CapybaraRepository repository ;

    //CapybaraService
    public CapybaraService(CapybaraRepository repository, StringUtilsService stringUtilsService) {
        this.repository = repository;
        this.stringUtilsService = stringUtilsService;
        this.repository.save(new Capybara("JAkub", "brown"));
        this.repository.save(new Capybara("MAks", "green"));
        this.repository.save(new Capybara("andrey", "black"));
    }

    //getCapybaraByName
    public List<Capybara> getCapybaraByName(String name) {
        Iterable<Capybara> capybaraByNAme = repository.findAll();
        capybaraByNAme.forEach(capybara -> stringUtilsService.goToLowerCaseExceptFirstLetter(String.valueOf(capybara)));
        return repository.findByName(name);
    }

    //getCapybaraByColor
    public List<Capybara> getCapybaraByColor(String color) {
        Iterable<Capybara> capybaraByColor = repository.findAll();
        capybaraByColor.forEach(capybara -> stringUtilsService.goToLowerCaseExceptFirstLetter(String.valueOf(capybara)));
        return repository.findByColor(color);
    }

    //getCapybaraList
    public Iterable<Capybara> getCapybaraList() {
       Iterable<Capybara> capybaraIterable = repository.findAll();
       capybaraIterable.forEach(capybara -> stringUtilsService.UpperCase(String.valueOf(capybara)));
        return repository.findAll();
    }


    //getCapybara
    public Capybara getCapybara(Long id) {
        Optional<Capybara> capybara = this.repository.findById(id);
        if (capybara.isEmpty()) {
            throw new CapybaraNotFoundException();
        }
        return capybara.get();
    }

    //delete
    public void delete(Integer id) {
        if(!repository.existsById(id.longValue())) {
            throw new CapybaraNotFoundException();
        }
        this.repository.deleteById(id);
    }


    //add
    public void add(Capybara capybara) {
        if(repository.existsById(capybara.getId())) {
            throw new CapybaraAlredyExist();
        }
        stringUtilsService.UpperCase(String.valueOf(capybara));
        this.repository.save(capybara);
    }


    public void update(String name, String color, Capybara newCapybara) {
        List<Capybara> capybaras = repository.findByName(name);
        if (capybaras.isEmpty()) {
            throw new CapybaraNotFoundException();
        }else if(capybaras.getFirst().getColor().equals(color)) {
            throw new CapybaraAlredyExist();
        }
        capybaras.stream().filter
                (c -> c.getColor().equals(color)).forEach(existing -> {
                    existing.setName(newCapybara.getName());
                    existing.setColor(newCapybara.getColor());
                    repository.save(existing);});
    }


}

