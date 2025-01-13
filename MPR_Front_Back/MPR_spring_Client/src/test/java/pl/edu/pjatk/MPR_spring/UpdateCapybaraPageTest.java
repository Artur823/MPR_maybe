package pl.edu.pjatk.MPR_spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.edu.pjatk.MPR_spring.pages.AddCapybaraPage;
import pl.edu.pjatk.MPR_spring.pages.HomePage;
import pl.edu.pjatk.MPR_spring.pages.UpdateCapybaraPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateCapybaraPageTest {
    private WebDriver driver;
    private UpdateCapybaraPage updateCapybaraPage;
    private HomePage homePage;
    private AddCapybaraPage addCapybaraPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        updateCapybaraPage = new UpdateCapybaraPage(driver);
        homePage = new HomePage(driver);
        addCapybaraPage = new AddCapybaraPage(driver);

        driver.get("http://localhost:8081/view/addForm");

        addCapybaraPage.setCapybaraNameInput("Cat");
        addCapybaraPage.clearCapybaraColorInput();
        addCapybaraPage.setCapybaraColorInput("white color test");
        addCapybaraPage.clickSaveCapybaraButton();

        driver.get(homePage.getUpdateUrl());
    }

    @Test
    public void updateCapybara() {
        updateCapybaraPage.clearFields();
        updateCapybaraPage.setcapybaraName("New capybara");
        updateCapybaraPage.setcapybaraColor("black color test");

        // Wait for the update button to be clickable (using class name)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Update")));
        updateButton.click();

        // Verify page title and updated text
        assertEquals(driver.getTitle(), "Capybaras");
        assertEquals(driver.findElement(By.xpath("//td[text()='New capybara']")).getText(), "New capybara");
    }

}