package pl.edu.pjatk.MPR_spring.Services;

import org.springframework.stereotype.Component;
import pl.edu.pjatk.MPR_spring.model.Capybara;

import java.util.ArrayList;
import java.util.List;

@Component
public class CapybaraService {
    List<Capybara> capybaraList = new ArrayList<>();

    public CapybaraService() {
        this.capybaraList.add(new Capybara("jakub", "brown"));
        this.capybaraList.add(new Capybara("maks", "green"));
        this.capybaraList.add(new Capybara("andrey", "black"));
    }
    public List<Capybara> getCapybaraList() {
        return this.capybaraList;
    }


    public void add(Capybara capybara) {
        this.capybaraList.add(capybara);
    }

    public Capybara getCapybara(Integer id) {
        return this.capybaraList.get(id);
    }
    public void delete(Integer id) {
        this.capybaraList.remove(id);
    }


    public void change(String name, String color, Capybara newCapybara) {
        for (int i = 0; i < capybaraList.size(); i++) {
            // Проверяем, соответствует ли капибара по имени и цвету
            Capybara existingCapybara = capybaraList.get(i);
            if (existingCapybara.getName().equals(name) && existingCapybara.getColor().equals(color)) {
                // Если нашлось совпадение, заменяем элемент
                capybaraList.set(i, newCapybara);
                return;
            }
        }
        // Если не нашли подходящий элемент
        throw new RuntimeException("Capybara with name " + name + " and color " + color + " not found");
    }

}

