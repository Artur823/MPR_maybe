//package pl.edu.pjatk.MPR_spring.pages;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//
//public class AddCapybaraPage {
//    private WebDriver webDriver;
//
//    @FindBy(id = "Capybara name input")
//    private WebElement CapybaraNameInput;
//
//    @FindBy(id = "Capybara color input")
//    private WebElement CapybaraColorInput;
//
//    @FindBy(id = "save new Capybara button")
//    private WebElement saveCapybaraButton;
//
//    public AddCapybaraPage(WebDriver webDriver) {
//        this.webDriver = webDriver;
//        PageFactory.initElements(webDriver, this);
//    }
//
//    public void setCapybaraNameInput(String capybaraName) {
//        CapybaraNameInput.sendKeys(capybaraName);
//    }
//
//    public void setCapybaraColorInput(String capybaraAge) {
//        CapybaraColorInput.sendKeys(capybaraAge);
//    }
//
//    public void clickSaveCapybaraButton() {
//        saveCapybaraButton.click();
//    }
//
//    public void clearCapybaraColorInput() {
//        CapybaraColorInput.clear();
//    }
//}