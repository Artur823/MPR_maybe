package pl.edu.pjatk.MPR_spring.selenium;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddFormTest {
    private WebDriver webDriver;

    @BeforeEach()
    public void setUp() {
        this.webDriver = new ChromeDriver();
    }

    @Test
    public void testAddForm() {
        AddFormPage addFormPage = new AddFormPage(webDriver);

        addFormPage.open()
                .fillInNameInput("Kapibara")
                .fillInColorInput("Red");
        ViewAllPage viewAllPage = addFormPage.sudmitForm();
        assertTrue(viewAllPage.isHeaderVisible());
    }
}
