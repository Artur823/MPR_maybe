package pl.edu.pjatk.MPR_spring.controller;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_spring.Service.CapybaraService;
import pl.edu.pjatk.MPR_spring.model.Capybara;

import java.util.List;
import java.util.Optional;

@Controller
public class MyViewController {
    private final CapybaraService capybaraService;

    public MyViewController(CapybaraService capybaraService) {
        this.capybaraService = capybaraService;
    }

    //Wszystko wyswietla
    @GetMapping("view/all")
    public String viewAllCapybaras(Model model) {
        model.addAttribute("capybaraList", capybaraService.getCapybaraList());
        return "viewAll";
    }

    //Dodawanie
    @GetMapping("view/addForm")
    public String displayAddForm(Model model) {
        model.addAttribute("capybara", new Capybara());
        return "viewAddForm";
    }

    @PostMapping("view/addForm")
    public String displayAddForm(@ModelAttribute Capybara capybara) {
        this.capybaraService.add(capybara);
        return "redirect:/view/all";
    }

    //usuwanie
    @GetMapping("view/deleteForm")
    public String displayDeleteForm(Model model) {
        model.addAttribute("capybaraList", capybaraService.getCapybaraList());
        return "viewDeleteForm";
    }

    @Transactional
    @PostMapping("view/deleteForm")
    public String displayDeleteForm(@RequestParam("id") Long id) {
        this.capybaraService.delete(Math.toIntExact(id)); //convert long into int
        return "redirect:/view/all";
    }


    //aktualizowanie
    @GetMapping("/view/updateForm")
    public String displayUpdateForm(Model model) {
        model.addAttribute("capybaraList", capybaraService.getCapybaraList());
        return "viewUpdateForm";
    }

    @PostMapping("/view/updateForm")
    public String updateCapybara(@RequestParam Long id,
                                 @RequestParam String newName,
                                 @RequestParam String newColor,
                                 Model model) {
        try {
            capybaraService.update(id, new Capybara(newName, newColor));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("capybaraList", capybaraService.getCapybaraList());
            return "viewUpdateForm";
        }

        return "redirect:/view/all";
    }
}