package pl.edu.pjatk.MPR_spring.Service;

import org.springframework.stereotype.Component;


@Component
public class StringUtilsService {

    public String UpperCase(String str) {
        return str.toUpperCase();
    }

    public String goToLowerCaseExceptFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str; // возвращаем строку как есть, если она пуста
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}

