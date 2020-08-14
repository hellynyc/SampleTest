package BBhelpers;

import BBenums.Browsers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

// Creating BrowserFabric class with getDriver function for getting different browsers
public class BrowserFabric {
    public static WebDriver getDriver(Browsers browser) throws NoSuchFieldException {
        switch (browser) {
            case FIREFOX:
                return getFirefoxDriver();
            case OPERA:
                return getOperaDriver();
            case CHROME:
                return getChromeDriver();
            default:
                throw new NoSuchFieldException("No such browser");
        }
    }

    // Setting each browser function to get the correct driver file & defining each browser with set window size
    private static WebDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("window-size=1400,1000");
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        return new ChromeDriver(options);
    }

    private static WebDriver getOperaDriver() {
        OperaOptions options = new OperaOptions();
        options.addArguments("--headless");
        options.addArguments("window-size=1400,1000");
        System.setProperty("webdriver.chrome.driver", "operadriver");
        return new OperaDriver(options);
    }

    private static WebDriver getFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        options.addArguments("--width=1400");
        options.addArguments("--height=1000");
        System.setProperty("webdriver.chrome.driver", "geckodriver");
        return new FirefoxDriver(options);
    }

}
