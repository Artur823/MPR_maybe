package pl.edu.pjatk.MPR_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/all")
    public String getAllCapybaras(Model model) {
        var capybaras = capybaraClientService.getCapybarasFromApi();
        System.out.println("Retrieved capybaras: " + capybaras);
        model.addAttribute("allCapybaras", capybaras);
        return "viewAll";
    }



    @PostMapping("/save")
    public String save(@ModelAttribute Capybara capybara) {
        System.out.println("Saving capybara: Name = " + capybara.getName() + ", Color = " + capybara.getColor());
        capybaraClientService.addCapybara(capybara);
        return "redirect:/view/all";
    }


    @GetMapping("/addForm")
    public String showAddForm(Model model) {
        model.addAttribute("capybara", new Capybara());
        return "viewAddForm";
    }


//    // Страница редактирования капибары
//    @GetMapping("/updateCapybara/{id}")
//    public String updateCapybara(@PathVariable Long id, Model model) {
//        Capybara capybara = capybaraClientService.getById(id); // Получаем капибару по ID для редактирования
//        model.addAttribute("capybara", capybara); // Передаем капибару в модель
//        return "viewAddForm"; // Отображаем форму для редактирования
//    }

    // Удаление капибары
    @PostMapping("/capybara/{id}")
    public String deleteCapybara(@PathVariable Long id) {
        capybaraClientService.deleteById(id); // Удаляем капибару
        return "redirect:/view/all"; // Перенаправляем на страницу со всеми капибарами
    }
}
