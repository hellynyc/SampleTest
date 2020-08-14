package Bareburger;

import BBenums.Browsers;
import BBhelpers.BrowserFabric;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.NoSuchElementException;

public class BaseTest {
    protected WebDriver driver;
    protected FluentWait<WebDriver> fluentWait;
    protected WebDriverWait wait;

    // Methods that will execute before each @Test
    @BeforeMethod
    public void startUp() throws NoSuchFieldException {
        driver = BrowserFabric.getDriver(Browsers.CHROME);
        driver.get("https://order.bareburger.com/");
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
    public void tearDown() {
        driver.quit();
    }
}
