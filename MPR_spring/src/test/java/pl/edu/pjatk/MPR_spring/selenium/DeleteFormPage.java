package pl.edu.pjatk.MPR_spring.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteFormPage {
    private WebDriver webDriver;

    @FindBy(id="id")
    private WebElement nameInput;

    @FindBy(id="submit")
    private WebElement submitButton;

    public DeleteFormPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public DeleteFormPage open (){
        this.webDriver.get("http://localhost:8080/view/deleteForm");
        return this;
    }

    public DeleteFormPage DeleteName(String text){
        this.nameInput.getAccessibleName().replaceAll("","null");
        return this;
    }

    public DeleteFormPage submitForm(){
        this.submitButton.click();
        return this;
    }
}
