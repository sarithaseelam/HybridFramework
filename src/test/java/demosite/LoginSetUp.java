package demosite;

import base.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.LoginSuccessPO;
import pageobjects.SignOnPO;

import java.io.IOException;
import java.util.List;


public class LoginSetUp extends Base {

    public WebDriver driver;
    public static Logger log= LogManager.getLogger(Base.class.getName());

    SignOnPO signOnPO;
    LoginSuccessPO loginSuccessPO;

    @BeforeTest
    public void init() throws IOException {
        driver=initializeDriver();
    }

    @Test(dataProvider = "getData")
    public void loginToMercury(String username,String password,String userType){
        driver.get(prop.getProperty("url"));

        signOnPO=new SignOnPO(driver);

        signOnPO.login(username,password);

        log.info(userType);

        validateLogin();

    }


    public void validateLogin(){

        loginSuccessPO=new LoginSuccessPO(driver);

        String val=loginSuccessPO.validateLoginSuccess();
        String val2=loginSuccessPO.validateLoginThanks();


        List<WebElement> successValues=driver.findElements(By.xpath("//h3"));
        List<WebElement> thankYouNote=driver.findElements(By.xpath("//b"));

        for(int i =0;i<successValues.size();i++){
            if(successValues.get(i).getText().equalsIgnoreCase("val")){
                Assert.assertEquals(successValues.get(i).getText(),"val");
            }
            else{
                continue;
            }
        }

        for(int i =0;i<thankYouNote.size();i++){
            if(thankYouNote.get(i).getText().equalsIgnoreCase("val2")){
                Assert.assertEquals(thankYouNote.get(i).getText(),"val2");
            }
            else{
                continue;
            }
        }


    }

    @AfterTest
    public void tearDown(){
        driver.close();
    }

    @DataProvider
    public Object[][] getData(){

        //Row stands for how many different data types test should run
        //column stands for how many values per each test

        Object[][] data=new Object[2][3];

        //0th row
        data[0][0]="mercury";
        data[0][1]="mercury";
        data[0][2]="Non restricted User";

        //1st row
        data[1][0]="test";
        data[1][1]="test";
        data[1][2]="Non restricted User";

        return data;

    }

}
