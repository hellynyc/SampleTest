package BBPageObjects;

import org.openqa.selenium.WebDriver;

// Repeated objects that can be shared to other Pages
public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
