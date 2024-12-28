//package pl.edu.pjatk.MPR_spring;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import pl.edu.pjatk.MPR_spring.pages.AddCapybaraPage;
//import pl.edu.pjatk.MPR_spring.pages.HomePage;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class AddCapybaraPageTest {
//    private WebDriver driver;
//    private AddCapybaraPage addCapybaraPage;
//    private HomePage homePage;
//
//    @BeforeEach
//    public void setUp() {
//        driver = new ChromeDriver();
//        driver.get("http://localhost:8080/addNewCapybara");
//        addCapybaraPage = new AddCapybaraPage(driver);
//        homePage = new HomePage(driver);
//    }
//
//    @Test
//    public void testAddCapybara() {
//        addCapybaraPage.setCapybaraNameInput("Capybara");
//        addCapybaraPage.clearCapybaraColorInput();
//        addCapybaraPage.setCapybaraColorInput("purple color test");
//        addCapybaraPage.clickSaveCapybaraButton();
//
//        assertEquals(driver.getTitle(), "Capybaras");
//
//
//        assertEquals(homePage.getCapybarasName(), "Capybara");
//        assertEquals(homePage.getCapybarasColor(), "test kolor");
//    }
//
//    @AfterEach
//    public void tearDown() {
//        driver.quit();
//    }
//}