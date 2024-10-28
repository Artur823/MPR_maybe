package pl.edu.pjatk.MPR_spring.Service;

import org.springframework.stereotype.Component;


@Component
public class StringUtilsService {

    public String UpperCase(String str) {
        return str.toUpperCase();
    }

    public String LowerCase(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1).toLowerCase();
    }
}
