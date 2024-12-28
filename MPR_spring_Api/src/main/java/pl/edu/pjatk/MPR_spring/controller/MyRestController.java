package pl.edu.pjatk.MPR_spring.controller;
//@GetMapping:  Позволяет получать данные с сервера по заданному URL.
//@PostMapping: Используется для отправки данных на сервер, например, для создания новых ресурсов.
//@RequestBody: Позволяет получать данные, отправленные в формате JSON или XML.
//@PathVariable: позволяет извлекать переменные из URI (путей) запроса. Используется для идентификаторов ресурсов.
//ResponseEntity:  это объект, который представляет собой полный HTTP-ответ, включая статусный код, заголовки и тело ответа
//Iterable — это часть коллекционного API, который определяет поведение объектов, которые могут быть перебраны с использованием цикла for-each
//Optional<T> является контейнером, который может содержать либо значение типа T, либо быть пустым (не содержать значения)
//@Transactional оборачивает метод в транзакцию, гарантируя, что все операции, выполняемые в его рамках, будут частью одной транзакции.

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_spring.Service.CapybaraService;
import pl.edu.pjatk.MPR_spring.model.Capybara;

import java.util.List;

//adnotacja do komunikowania z secia
@RestController
public class MyRestController   {

    @Autowired
    private CapybaraService capybaraService;

    //służy do automatycznego wstrzykiwania zależności do komponentów Springa
    @Autowired
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


    @GetMapping("capybara/all")// <- endpoint
    public ResponseEntity<Iterable<Capybara>> getAll(){
        return new ResponseEntity<>(this.capybaraService.getCapybaraList(), HttpStatus.OK);
    }


    @GetMapping("capybara/{id}")// <- endpoint
    public ResponseEntity<Capybara> get(@PathVariable Long id ){
        return new ResponseEntity<>(this.capybaraService.getCapybara(id),HttpStatus.OK);
    }

    @GetMapping("/capybara/pdf/{id}")
    public ResponseEntity<byte[]> getPdfCapybara(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return capybaraService.getPdfCapybara(id);
    }

    @PostMapping("capybara")
    public ResponseEntity<Capybara> add(@RequestBody Capybara capybara){
        this.capybaraService.add(capybara);
        return new ResponseEntity<>(capybara, HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("capybara/{id}")
    public ResponseEntity<Capybara> delete(@PathVariable Integer id) {
        this.capybaraService.delete(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //то есть Patch
    @PutMapping("capybara/{id}")
    public ResponseEntity<Capybara> update(@PathVariable Long id ,  @RequestBody Capybara newCapybara) throws Exception {
        this.capybaraService.update( id ,newCapybara);
        return new ResponseEntity<>(newCapybara, HttpStatus.OK);
    }

    //API + CLient --------------------------------------------------------------------------------------------------------------

    @GetMapping("/client/capybara/all")
    public List<Capybara> getCapybarasFromApi() {
        return (List<Capybara>) capybaraService.getCapybaraList();
    }


    @Transactional
    @DeleteMapping("/client/capybara/{id}")
    public ResponseEntity<Void> deleteCapybaraById(@PathVariable Long id) {
        if (!capybaraService.delete(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/client/capybara")
    public ResponseEntity<Capybara> addCapybara(@RequestBody Capybara capybara) {
        System.out.println("Received Capybara: " + capybara);
        capybaraService.add(capybara);
        return new ResponseEntity<>(capybara, HttpStatus.CREATED);
    }





}