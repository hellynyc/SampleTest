package BareburgerPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    // Web elements Xpath for login page
    By enterEmailBy = By.xpath("//*[@placeholder=\"Enter Email Here\"]");
    By enterPasswordBy = By.xpath("//*[@type=\"password\"]");
    By submitBy = By.xpath("//*[@type=\"submit\"]");
    By wrongPasswordErrorBy = By.xpath("//*[@data-value=\"body1_baseWarning\"]");

    // Constructor initializing WebDriver as parameter
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Getters to find web elements
    public WebElement getEmail() {
        return driver.findElement(enterEmailBy);
    }

    public WebElement getPassword() {
        return driver.findElement(enterPasswordBy);
    }

    public WebElement getSubmitButton() {
        return driver.findElement(submitBy);
    }

    // Checking to see if there is a password error
    public boolean isError() {
        var errors = driver.findElements(wrongPasswordErrorBy);
        return errors.size()==0;
    }

    // Login function where user input their credentials and click submit
    public MainPage loginToBareburger(String email, String password) {
        getEmail().sendKeys(email);
        getPassword().sendKeys(password);
        getSubmitButton().click();
        return new MainPage(driver);
    }
}
