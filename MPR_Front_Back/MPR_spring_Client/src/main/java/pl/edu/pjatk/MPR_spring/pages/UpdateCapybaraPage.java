package pl.edu.pjatk.MPR_spring.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UpdateCapybaraPage {
    private WebDriver driver;

    @FindBy(id="Capybara name update field")
    private WebElement capybaraName;

    @FindBy(id="Capybara age update field")
    private WebElement capybaraAge;

    @FindBy(id="update Capybara button")
    private WebElement updateCapybaraButton;

    public UpdateCapybaraPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setcapybaraColor(String color) {
        capybaraAge.sendKeys(color);
    }

    public void setcapybaraName(String catName) {
        this.capybaraName.sendKeys(catName);
    }

    public void updateCapybaraButton(){
    updateCapybaraButton.click();
    }

    public void clearFields() {
        capybaraAge.clear();
        capybaraName.clear();
    }
}
