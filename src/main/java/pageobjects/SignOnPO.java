package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignOnPO {

    public WebDriver driver;

    @FindBy(xpath="//input[@name='userName']")
    private WebElement userName;

    @FindBy(xpath="//input[@name='password']")
    private WebElement password;

    @FindBy(xpath="//input[@name='submit']")
    private WebElement submit;


    public SignOnPO(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void login(String username,String pass){
        userName.sendKeys(username);
        password.sendKeys(pass);
        submit.click();
    }
}
