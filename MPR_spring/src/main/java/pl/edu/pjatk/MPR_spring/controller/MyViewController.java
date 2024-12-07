package pl.edu.pjatk.MPR_spring.controller;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_spring.Service.CapybaraService;
import pl.edu.pjatk.MPR_spring.model.Capybara;

import java.util.List;

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
    @GetMapping("view/updateForm")
    public String displayUpdateForm(@RequestParam(value = "name", required = false) String name, Model model) {
        if (name != null && !name.isEmpty()) {
            List<Capybara> capybaras = capybaraService.getCapybaraByName(name);
            if (!capybaras.isEmpty()) {
                Capybara capybara = capybaras.get(0);  // Jeśli jest kilka kapibar o tej samej nazwie, wybierz pierwszą

                model.addAttribute("capybara", capybara);
            } else {
                model.addAttribute("error", "Capybara not found");
            }
        }
        model.addAttribute("capybaraList", capybaraService.getCapybaraList());
        return "viewUpdateForm";
    }

    @PostMapping("view/updateForm")
    public String updateCapybara(@RequestParam("name") String name,
                                 @RequestParam("newName") String newName,
                                 @RequestParam("newColor") String newColor,
                                 Model model) {

        List<Capybara> capybaras = capybaraService.getCapybaraByName(name);

        if (capybaras.isEmpty()) {
            model.addAttribute("error", "Capybara not found.");
            model.addAttribute("capybaraList", capybaraService.getCapybaraList());
            return "viewUpdateForm";
        }

        Capybara capybara = capybaras.get(0);

        if (!newName.equals(name)) {
            List<Capybara> existingCapybaras = capybaraService.getCapybaraByName(newName);
            if (!existingCapybaras.isEmpty()) {
                model.addAttribute("error", "A Capybara with this name already exists.");
                model.addAttribute("capybaraList", capybaraService.getCapybaraList());
                return "viewUpdateForm";
            }
        }

        // Обновляем имя и цвет
        capybara.setName(newName);
        capybara.setColor(newColor);
        capybaraService.update(capybara.getName(), capybara.getColor(), capybara);

        return "redirect:/view/all";
    }
}
