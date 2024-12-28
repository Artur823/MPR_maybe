//package pl.edu.pjatk.MPR_spring;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import pl.edu.pjatk.MPR_spring.pages.HomePage;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class HomePageTest {
//    private WebDriver driver;
//    private HomePage homePage;
//
//    @BeforeEach
//    public void setUp() {
//        driver = new ChromeDriver();
//        driver.get("http://localhost:8080/");
//        homePage = new HomePage(driver);
//    }
//
//    @Test
//    public void redirectToAddCapybaraPage() {
//        homePage.clickAddCapybaraButton();
//        assertEquals(driver.getTitle(), "Add new capybara");
//    }
//
//    @AfterEach
//    public void tearDown() {
//        driver.quit();
//    }
//}