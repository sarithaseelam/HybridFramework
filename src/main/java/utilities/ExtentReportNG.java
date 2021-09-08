package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

    static ExtentReports extent;

    public static ExtentReports getReportObject(){

        String path="C:\\Users\\SiddiqAb\\OneDrive - Government of Ontario\\Desktop\\hybridframework\\reports\\index.html";
        ExtentSparkReporter reporter=new ExtentSparkReporter(path);
        reporter.config().setReportName("Mercury Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        extent=new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("QA personnel","Abu");
        return extent;
    }
}
