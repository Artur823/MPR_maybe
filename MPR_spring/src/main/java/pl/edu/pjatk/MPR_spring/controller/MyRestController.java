package pl.edu.pjatk.MPR_spring.controller;
//@GetMapping: Позволяет получать данные с сервера по заданному URL.
//@PostMapping: Используется для отправки данных на сервер, например, для создания новых ресурсов.
//@RequestBody:Позволяет получать данные, отправленные в формате JSON или XML.
//@PathVariable:озволяет извлекать переменные из URI (путей) запроса. Используется для идентификаторов ресурсов.


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_spring.Service.CapybaraService;
import pl.edu.pjatk.MPR_spring.Service.StringUtilsService;
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
        public ResponseEntity<List<Capybara>> getByName(@PathVariable String name){
            return new ResponseEntity<>(this.capybaraService.getCapybaraByName(name), HttpStatus.OK);
        }

        @GetMapping("capybara/color/{color}")
        public ResponseEntity<List<Capybara>> getByColor(@PathVariable String color){
        return new ResponseEntity<>(this.capybaraService.getCapybaraByColor(color), HttpStatus.OK);
        }


        //позволяет работать с коллекциями объектов, не заботясь о конкретном типе коллекции (например, List, Set)
    @GetMapping("capybara/all")// <- endpoint
    public ResponseEntity<Iterable<Capybara>> getAll(){
        return new ResponseEntity<>(this.capybaraService.getCapybaraList(), HttpStatus.OK);
    }


    //это контейнерный объект, который может содержать либо значение(or null)
    @GetMapping("capybara/{id}")// <- endpoint
    public ResponseEntity<Capybara> get(@PathVariable Long id ){
        return new ResponseEntity<>(this.capybaraService.getCapybara(id),HttpStatus.OK);

    }

    @PostMapping("capybara")
        public ResponseEntity<Capybara> addCapybara(@RequestBody Capybara capybara){
        this.capybaraService.add(capybara);
        return new ResponseEntity<>(capybara, HttpStatus.OK);
    }
    // Метод для удаления капибары по id
    @DeleteMapping("capybara/{id}")
    public ResponseEntity<Capybara> deleteCapybara(@PathVariable Integer id) {
        this.capybaraService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //то есть Patch
    @PutMapping("capybara/{name}/{color}")
    public ResponseEntity<Capybara> changeCapybara(@PathVariable String name, @PathVariable String color, @RequestBody Capybara newCapybara) {
       this.capybaraService.update(name, color, newCapybara);
       return new ResponseEntity<>(newCapybara, HttpStatus.OK);
    }


}
