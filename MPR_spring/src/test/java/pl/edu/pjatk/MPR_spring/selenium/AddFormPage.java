package pl.edu.pjatk.MPR_spring.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddFormPage {
   private WebDriver webDriver;
    @FindBy(id="name")
    private WebElement nameInput;

    @FindBy(id="color")
    private WebElement colorInput;

    @FindBy(id="submit")
    private WebElement submitButton;

    public AddFormPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public AddFormPage open (){
        this.webDriver.get("http://localhost:8080/view/addForm");
        return this;
    }

    public AddFormPage fillInNameInput(String text){
        this.nameInput.sendKeys(text);
        return this;
    }

    public AddFormPage fillInColorInput(String text){
        this.colorInput.sendKeys(text);
        return this;
    }

    public ViewAllPage sudmitForm() {

        this.submitButton.click();

        return new ViewAllPage(this.webDriver);
    }
}
