package pl.edu.pjatk.MPR_spring.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewAllPage {
    private WebDriver webDriver;

    @FindBy(tagName = "h1")
    private WebElement header;

    public ViewAllPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isHeaderVisible() {
        return this.header.isDisplayed();
    }
    public ViewAllPage open(){
        this.webDriver.get("http://localhost:8080/view/all");
        return this;
    }
}
