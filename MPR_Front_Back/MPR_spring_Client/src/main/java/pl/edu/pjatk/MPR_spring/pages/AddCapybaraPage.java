package pl.edu.pjatk.MPR_spring.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCapybaraPage {
    private WebDriver webDriver;

    @FindBy(id = "name")
    private WebElement capybaraNameInput;

    @FindBy(id = "color")
    private WebElement capybaraColorInput;

    @FindBy(css = "button[type='submit']")
    private WebElement saveCapybaraButton;

    public AddCapybaraPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void setCapybaraNameInput(String capybaraName) {
        capybaraNameInput.sendKeys(capybaraName);
    }

    public void setCapybaraColorInput(String capybaraColor) {
        capybaraColorInput.sendKeys(capybaraColor);
    }

    public void clickSaveCapybaraButton() {
        saveCapybaraButton.click();
    }

    public void clearCapybaraColorInput() {
        capybaraColorInput.clear();
    }
}
