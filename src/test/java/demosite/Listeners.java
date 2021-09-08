package demosite;

import base.Base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.ExtentReportNG;

public class Listeners extends Base implements ITestListener {

    ExtentTest test;
    ExtentReports extent= ExtentReportNG.getReportObject();

    ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        test=extent.createTest(iTestResult.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        extentTest.get().log(Status.PASS,"Test was successful");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        extentTest.get().fail(iTestResult.getThrowable());
        WebDriver driver=null;

        String testMethod=iTestResult.getMethod().getMethodName();

        try{
            driver=(WebDriver) iTestResult.getTestClass().getRealClass().getDeclaredField("driver").
                    get(iTestResult.getInstance());
        }
        catch(Exception e){
            e.printStackTrace();
        }

        try{
            extentTest.get().addScreenCaptureFromPath(getScreenShotPath(testMethod,driver));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extent.flush();
    }
}
