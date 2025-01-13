package pl.edu.pjatk.MPR_spring.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
   private WebDriver webDriver;

    @FindBy(linkText = "Add New Capybara")
    private WebElement addCapybaraButton;

    @FindBy(id = "capybaras name field")
    private WebElement capybarasNameField;

   @FindBy(id = "capybaras age field")
    private WebElement capybarasAgeField;

   @FindBy(id = "capybaras update button")
    private WebElement capybarasUpdateButton;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public void clickAddCapybaraButton() {
        addCapybaraButton.click();
    }
    public String getCapybarasName(){
        return capybarasNameField.getText();
    }
    public String getCapybarasColor(){
        return capybarasAgeField.getText();
    }
    public String getUpdateUrl(){
        return capybarasUpdateButton.getAttribute("href");
    }
}