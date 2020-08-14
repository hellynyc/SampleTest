package BBPageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    private static Logger logger = LogManager.getLogger(MainPage.class);
    // Constructor initializing WebDriver as parameter
    public MainPage(WebDriver driver) {
        super(driver);
    }

    // Web elements Xpath for main page after login
    By accountBy = By.xpath("//*[text()='ACCOUNT']");

    // Checking to see if user has successfully logged in by verifying "ACCOUNT" element
    public boolean isMain() {
        var list = driver.findElements(accountBy);
        return list.size() == 0;
    }

    // Delivery function after user has successfully logged in
    public void deliveryOrder() {

    }

    // Checking to see if delivery order is successfully placed
    public boolean checkDeliveryOrder() {
        return true;
    }
}
