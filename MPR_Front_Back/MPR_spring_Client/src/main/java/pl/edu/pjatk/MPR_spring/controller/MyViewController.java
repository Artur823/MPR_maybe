package pl.edu.pjatk.MPR_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_spring.model.Capybara;
import pl.edu.pjatk.MPR_spring.service.CapybaraClientService;




@Controller
@RequestMapping("/view")
public class MyViewController {

    private final CapybaraClientService capybaraClientService;

    @Autowired
    public MyViewController(CapybaraClientService capybaraClientService) {
        this.capybaraClientService = capybaraClientService;
    }

    @GetMapping("/all")
    public String getAllCapybaras(Model model) {
        var capybaras = capybaraClientService.getCapybarasFromApi();
        model.addAttribute("allCapybaras", capybaras);
        return "viewAll";  // Имя представления
    }


    @GetMapping("/getByName")
    public String getCapybaraByName(@RequestParam(value = "name", required = false) String name, Model model) {
        if (name == null || name.isEmpty()) {
            model.addAttribute("allCapybaras", capybaraClientService.getAll());
        } else {
            model.addAttribute("allCapybaras", capybaraClientService.getByName(name));
        }
        return "viewAll";
    }

    @GetMapping("/addForm")
    public String addNewCapybara(Model model) {
        model.addAttribute("capybara", new Capybara());
        return "viewAddForm";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Capybara capybara) {
        if (capybara.getId() != null) {
            Capybara existingCapybara = capybaraClientService.getById(capybara.getId());
            existingCapybara.setName(capybara.getName());
            existingCapybara.setColor(capybara.getColor());
            existingCapybara.setId(capybara.getId());
            existingCapybara.generateHashCode();
            capybaraClientService.updateCapybara(existingCapybara);
        } else {
            capybaraClientService.addCapybara(capybara);
        }
        return "redirect:/all";
    }


    @GetMapping("/deleteCapybaraByName/{name}")
    public String deleteCapybaraByName(@PathVariable(value = "name") String name) {
        capybaraClientService.deleteByName(name);
        return "redirect:/all";
    }

    @GetMapping("/updateCapybara/{id}")
    public String updateCapybara(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("capybara", capybaraClientService.getById(id));
        return "viewUpdateForm";
    }
}