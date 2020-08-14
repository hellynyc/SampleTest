package BBTests;

import BBenums.Browsers;
import BBhelpers.BrowserFabric;
import BBhelpers.GetScreenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.NoSuchElementException;

public class BaseTest {
    protected WebDriver driver;
    protected FluentWait<WebDriver> fluentWait;
    protected WebDriverWait wait;

    // Parameters to choose different browsers
    @Parameters({"browser"})
    // Methods that will execute before each @Test
    @BeforeMethod
    public void startUp(String browserName) throws NoSuchFieldException {

        // Creating a browser instance
        Browsers browser;

        // Telling testng xml file which browser to use
        if(browserName.equals("CHROME")) {
            browser = Browsers.CHROME;
        } else {
            browser = Browsers.FIREFOX;
        }

        // Calling the driver to get the specific driver from BrowserFabric class
        driver = BrowserFabric.getDriver(browser);

        // Calling the driver to open up Bareburger ordering page
        driver.get("https://order.bareburger.com/");

        // Explicit waits
        wait = new WebDriverWait(driver, 20);
        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(NoSuchElementException.class);

        // Web elements Xpath for opening login window
        By loginBy = By.xpath("//*[@alt=\"bareburger Icon\"]");
        By enterEmailBy = By.xpath("//*[@placeholder=\"Enter Email Here\"]");

        // Opening the login window by clicking on Login button
        var loginButton = driver.findElement(loginBy);
        loginButton.click();

        // Waiting to verify email field before entering credentials
        fluentWait.until(x->x.findElement(enterEmailBy).isDisplayed());
    }

    // Methods that will execute after each @Test
    @AfterMethod
    // Teardown function that includes ITestResult Listener
    public void tearDown(ITestResult iTestResult) {
        if(iTestResult.getStatus()==iTestResult.FAILURE) {
            GetScreenshot.capture(driver, iTestResult.getName());
        }
        driver.quit();
    }
}
