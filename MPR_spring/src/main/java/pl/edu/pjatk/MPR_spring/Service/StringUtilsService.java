package pl.edu.pjatk.MPR_spring.Service;

import org.springframework.stereotype.Component;


@Component
public class StringUtilsService {

    public String UpperCase(String str) {
        return str.toUpperCase();
    }

    public String goToLowerCaseExceptFirstLetter(String str) {
        return str.substring(0, 1) + str.substring(1).toLowerCase();
    }
}

