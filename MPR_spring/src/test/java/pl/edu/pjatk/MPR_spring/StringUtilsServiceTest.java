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
        stringUtilsService.UpperCase(String.valueOf(capybara));
        assertEquals("capibara", capybara.getName());
        assertEquals("WHITE", capybara.getColor());
    }

    @Test
    public void goToUpperCaseWhenAllLettersAreMixCase() {
        Capybara capybara = new Capybara("CapibarA", "WhiTe");
        stringUtilsService.UpperCase(String.valueOf(capybara));
        assertEquals("CAPIBARA", capybara.getName());
        assertEquals("WHITE", capybara.getColor());
    }

    @Test
    public void goToLowerCaseExceptFirstLetterWhenAllLettersAreUpperCase() {
        Capybara capybara = new Capybara("CAPIBARA", "WHITE");
        stringUtilsService.goToLowerCaseExceptFirstLetter(String.valueOf(capybara));
        assertEquals("Capibara", capybara.getName());
        assertEquals("White", capybara.getColor());
    }

    @Test
    public void goToLowerCaseExceptFirstLetterWhenAllLettersAreLowerCase() {
        Capybara capybara = new Capybara("capibara", "white");
        stringUtilsService.goToLowerCaseExceptFirstLetter(String.valueOf(capybara));
        assertEquals("Capibara", capybara.getName());
        assertEquals("White", capybara.getColor());
    }

    @Test
    public void goToLowerCaseExceptFirstLetterWhenAllLettersAreMixCase() {
        Capybara capybara = new Capybara("cAPIBARa", "WHiTe");
        stringUtilsService.goToLowerCaseExceptFirstLetter(String.valueOf(capybara));
        assertEquals("Capibara", capybara.getName());
        assertEquals("White", capybara.getColor());
    }
}
