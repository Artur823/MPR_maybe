package pl.edu.pjatk.MPR_spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pjatk.MPR_spring.model.Capybara;
import pl.edu.pjatk.MPR_spring.Service.StringUtilsService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilsServiceTest {
    private StringUtilsService stringUtilsService;
    @BeforeEach
    public void setUp() {
        this.stringUtilsService = new StringUtilsService();
    }

    @Test
    public void goToUpperCaseWhenAllLettersAreUpperCase() {
        Capybara capybara = new Capybara("CAPIBARA", "WHITE");
        stringUtilsService.UpperCase(String.valueOf(capybara));
        assertEquals("CAPIBARA", capybara.getName());
        assertEquals("WHITE", capybara.getColor());
    }

    @Test
    public void goToUpperCaseWhenAllLettersAreLowerCase() {
        Capybara capybara = new Capybara("capibara", "white");
        capybara.setColor(stringUtilsService.UpperCase(capybara.getColor()));
        assertEquals("capibara", capybara.getName());
        assertEquals("WHITE", capybara.getColor());
    }


    @Test
    public void goToUpperCaseWhenAllLettersAreMixCase() {
        Capybara capybara = new Capybara("CapibarA", "WhiTe");
        capybara.setName(stringUtilsService.UpperCase(String.valueOf(capybara.getName())));
        capybara.setColor(stringUtilsService.UpperCase(String.valueOf(capybara.getColor())));
        assertEquals("CAPIBARA", capybara.getName());
        assertEquals("WHITE", capybara.getColor());
    }

    @Test
    public void goToLowerCaseExceptFirstLetterWhenAllLettersAreUpperCase() {
        Capybara capybara = new Capybara("CAPIBARA", "WHITE");
        capybara.setName(stringUtilsService.goToLowerCaseExceptFirstLetter(String.valueOf(capybara.getName())));
        capybara.setColor(stringUtilsService.goToLowerCaseExceptFirstLetter(String.valueOf(capybara.getColor())));
        assertEquals("Capibara", capybara.getName());
        assertEquals("White", capybara.getColor());
    }

    @Test
    public void goToLowerCaseExceptFirstLetterWhenAllLettersAreLowerCase() {
        Capybara capybara = new Capybara("capibara", "white");
        capybara.setName(stringUtilsService.goToLowerCaseExceptFirstLetter(capybara.getName()));
        capybara.setColor(stringUtilsService.goToLowerCaseExceptFirstLetter((capybara.getColor())));
        assertEquals("Capibara", capybara.getName());
        assertEquals("White", capybara.getColor());
    }

    @Test
    public void goToLowerCaseExceptFirstLetterWhenAllLettersAreMixCase() {
        Capybara capybara = new Capybara("cAPIBARa", "WHiTe");
        capybara.setName(stringUtilsService.goToLowerCaseExceptFirstLetter(capybara.getName()));
        capybara.setColor(stringUtilsService.goToLowerCaseExceptFirstLetter((capybara.getColor())));
        assertEquals("Capibara", capybara.getName());
        assertEquals("White", capybara.getColor());
    }
}
