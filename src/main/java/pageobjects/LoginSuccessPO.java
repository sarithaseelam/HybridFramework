package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSuccessPO {

    public WebDriver driver;

    @FindBy(xpath="//h3[contains(text(),'Login Successfully')]")
    private WebElement loginSuccessHeader;

    @FindBy(xpath="//b[contains(text(),' Thank you for Loggin.')]")
    private WebElement loginThankYouNote;


    public LoginSuccessPO(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }

    public String validateLoginSuccess(){

        String loginSuccessMessage=loginSuccessHeader.getText();
        return loginSuccessMessage;
    }


    public String validateLoginThanks(){

        String thankYou=loginThankYouNote.getText();
        return thankYou;
    }
}
