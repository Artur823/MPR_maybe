package pl.edu.pjatk.MPR_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_spring.model.Capybara;
import pl.edu.pjatk.MPR_spring.service.CapybaraClientService;



//8081 port
@Controller
@RequestMapping("/view")
public class MyViewController {

    @Autowired
    private CapybaraClientService capybaraClientService;

    @Autowired
    public MyViewController(CapybaraClientService capybaraClientService) {
        this.capybaraClientService = capybaraClientService;
    }

    //view All
    @GetMapping("/all")
    public String getAllCapybaras(Model model) {
        var capybaras = capybaraClientService.getCapybarasFromApi();
        System.out.println("Retrieved capybaras: " + capybaras);
        model.addAttribute("allCapybaras", capybaras);
        return "viewAll";
    }


    //save -> add
    @PostMapping("/save")
    public String save(@ModelAttribute Capybara capybara) {
        System.out.println("Saving capybara: Name = " + capybara.getName() + ", Color = " + capybara.getColor());
        capybaraClientService.addCapybara(capybara);
        return "redirect:/view/all";
    }

    //addView
    @GetMapping("/addForm")
    public String showAddForm(Model model) {
        model.addAttribute("capybara", new Capybara());
        return "viewAddForm";
    }


    //update
    @GetMapping("/updateCapybara/{id}")
    public String updateCapybara(@PathVariable Long id, Model model) {
        Capybara capybara = capybaraClientService.getById(id);

        if (capybara == null) {
            return "errorPage";  // Handle not found case
        }

        model.addAttribute("capybara", capybara);
        return "viewUpdateForm";
    }

    @PostMapping("/updateCapybara/{id}")
    public String processUpdateCapybara(@PathVariable Long id, @ModelAttribute Capybara capybara, Model model) {
        capybaraClientService.updateCapybara(id, capybara);
        return "redirect:/view/all";
    }


    // delete
    @PostMapping("/capybara/{id}")
    public String deleteCapybara(@PathVariable Long id) {
        capybaraClientService.deleteById(id);
        return "redirect:/view/all";
    }
}
