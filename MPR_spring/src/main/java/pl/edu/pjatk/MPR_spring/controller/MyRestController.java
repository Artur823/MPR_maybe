package pl.edu.pjatk.MPR_spring.controller;
//@GetMapping: Позволяет получать данные с сервера по заданному URL.
//@PostMapping: Используется для отправки данных на сервер, например, для создания новых ресурсов.
//@RequestBody:Позволяет получать данные, отправленные в формате JSON или XML.
//@PathVariable:озволяет извлекать переменные из URI (путей) запроса. Используется для идентификаторов ресурсов.


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_spring.Service.CapybaraService;
import pl.edu.pjatk.MPR_spring.model.Capybara;

import java.util.List;
import java.util.Optional;


@RestController //adnotacja do komunikowania z secia
public class MyRestController   {
    private CapybaraService capybaraService;


    @Autowired //dzieki niemu spring tworzy automatychne nowy obiekt
    public MyRestController(CapybaraService capybaraService) {
        this.capybaraService = capybaraService;
    }


    @GetMapping ("capybara/name/{name}")
        public List<Capybara> getByName(@PathVariable String name){
            return this.capybaraService.getCapybaraByName(name);
        }

        @GetMapping("capybara/color/{color}")
        public List<Capybara> getByColor(@PathVariable String color){
        return this.capybaraService.getCapybaraByColor(color);
        }

    @GetMapping("capybara/all")// <- endpoint
    public Iterable<Capybara> getAll(){
        return this.capybaraService.getCapybaraList();

    }

    @GetMapping("capybara/{id}")// <- endpoint
    public Optional<Capybara> get(@PathVariable Long id){
        return this.capybaraService.getCapybara(id);

    }

    @PostMapping("capybara")
    public void addCapybara(@RequestBody Capybara capybara) {
        this.capybaraService.add(capybara);
    }

    // Метод для удаления капибары по id
    @DeleteMapping("capybara/{id}")
    public void deleteCapybara(@PathVariable Integer id) {
        this.capybaraService.delete(id);
    }

    @PutMapping("capybara/{name}/{color}")
    public void changeCapybara(@PathVariable String name, @PathVariable String color, @RequestBody Capybara newCapybara) {
        this.capybaraService.change(name, color, newCapybara);
    }


}
