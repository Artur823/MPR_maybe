package pl.edu.pjatk.MPR_spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager; // Исправленный импорт
import pl.edu.pjatk.MPR_spring.pages.AddCapybaraPage;
import pl.edu.pjatk.MPR_spring.pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCapybaraPageTest {
    private WebDriver driver;
    private AddCapybaraPage addCapybaraPage;

    @BeforeEach
    public void setUp() {
        // Используем WebDriverManager для управления драйвером Chrome
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost:8081/view/addForm");
        addCapybaraPage = new AddCapybaraPage(driver);
        HomePage homePage = new HomePage(driver);
    }

    @Test
    public void testAddCapybara() {
        // Заполнение формы
        addCapybaraPage.setCapybaraNameInput("Capybara");
        addCapybaraPage.clearCapybaraColorInput();
        addCapybaraPage.setCapybaraColorInput("Purple");

        // Нажатие кнопки "Save"
        addCapybaraPage.clickSaveCapybaraButton();

        // Проверка редиректа (ожидаемый заголовок страницы после сохранения)
        assertEquals("Capybara List", driver.getTitle());
    }

}