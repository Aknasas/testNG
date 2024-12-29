package mit4201.a3.pom.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import mit4201.a3.pom.pages.BasePage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestingUtils {
    protected WebDriver driver;
    private static ExtentReports extent;
    private ExtentTest test;

    @BeforeSuite(alwaysRun = true)
    public static ExtentReports getExtentInstance() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportFileName = "ExtentReport_Global_" + timestamp + ".html";

            // Initialize ExtentSparkReporter
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./reports/" + reportFileName);
            sparkReporter.config().setReportName("Automation Test Report");
            sparkReporter.config().setDocumentTitle("Test Execution Report");

            // Initialize ExtentReports
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Add system/environment info
            extent.setSystemInfo("Tester", "Your Name");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        }
        return extent;
    }

    @BeforeClass
    public void initBrowser(ITestContext context) {
        // Set up ChromeDriver
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // Update with your ChromeDriver path
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Initialize a class-level test in Extent Report
        String className = context.getCurrentXmlTest().getName();
        test = getExtentInstance().createTest(className + " - " + this.getClass().getSimpleName());
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

    public BasePage loadBasePage() {
        return new BasePage(driver);
    }

    @AfterMethod
    public void takeScreenshot(ITestResult result) {
        try {
            File screenshotDir = new File("screenshots");
            if (!screenshotDir.exists()) {
                boolean dirCreated = screenshotDir.mkdirs();
                System.out.println("Screenshots directory created: " + dirCreated);
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String testName = result.getName();
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String dest = "screenshots/" + testName + "_" + timestamp + ".png";
            FileUtils.copyFile(source, new File(dest));

            // Add screenshot and status to Extent Report
            if (result.getStatus() == ITestResult.FAILURE) {
                test.fail("Test failed: " + result.getThrowable())
                        .addScreenCaptureFromPath(dest);
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                test.pass("Test passed")
                        .addScreenCaptureFromPath(dest);
            } else if (result.getStatus() == ITestResult.SKIP) {
                test.skip("Test skipped: " + result.getThrowable())
                        .addScreenCaptureFromPath(dest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void createTestReport(ITestResult result) {
        // Initialize a method-level test in Extent Report
        String testName = result.getMethod().getMethodName();
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportFileName = "ExtentReport_" + testName + "_" + timestamp + ".html";

        ExtentSparkReporter methodSparkReporter = new ExtentSparkReporter("./reports/" + reportFileName);
        methodSparkReporter.config().setReportName("Automation Test Report - " + testName);
        methodSparkReporter.config().setDocumentTitle("Test Execution Report - " + testName);

        extent.attachReporter(methodSparkReporter);
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }
}
