package BBPageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    // Logging add-ons to debug and log the tests
    private static Logger logger = LogManager.getLogger(LoginPage.class);

    // Web elements Xpath for login page
    By enterEmailBy = By.xpath("//*[@placeholder=\"Enter Email Here\"]");
    By enterPasswordBy = By.xpath("//*[@type=\"password\"]");
    By submitBy = By.xpath("//*[@type=\"submit\"]");
    By wrongPasswordErrorBy = By.xpath("//*[@data-value=\"body1_baseWarning\"]");

    // Constructor initializing WebDriver as parameter
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Getter functions to find web elements
    public WebElement getEmail() {
        return driver.findElement(enterEmailBy);
    }

    public WebElement getPassword() {
        return driver.findElement(enterPasswordBy);
    }

    public WebElement getSubmitButton() {
        return driver.findElement(submitBy);
    }

    // Login function where user can input their credentials and click submit
    // With logger outputs after each function has ran
    public MainPage loginToBareburger(String email, String password) {
        logger.info("test started");
        getEmail().sendKeys(email);
        logger.trace("email entered");
        getPassword().sendKeys(password);
        logger.trace("password entered");
        getSubmitButton().click();
        logger.info("login button clicked");
        return new MainPage(driver);
    }

    // Checking to see if there is a password error
    public boolean isError() {
        var errors = driver.findElements(wrongPasswordErrorBy);
        return errors.size()==0;
    }
}
