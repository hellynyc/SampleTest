package Bareburger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;

public class OrderPickupTest {

    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private FluentWait<WebDriver> fluentWait;

    // Methods that will execute before each @Test
    @BeforeMethod
    public void startUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.get("https://order.bareburger.com/location/Court-Street");

        webDriverWait = new WebDriverWait(driver, 20);
        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        // Web elements Xpath for verifying the Court Street ordering page has loaded
        By courtStOrderingPageBy = By.xpath("//*[@class=\"sc-gqjmRU bSlixc StandardWeb_locationInfo__1Wi5J\"]");

        // Waiting to verify Court Street ordering page has loaded
        fluentWait.until(x->x.findElement(courtStOrderingPageBy).isDisplayed());
    }

    // Methods that execute after each @Test
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    // Test to verify the user can order a special burger with add-ons
    @Test
    public void addSpecialsToCart() throws InterruptedException {

        // Web elements Xpath for ordering items and view cart elements
        By specialItemBy = By.xpath("//*[@id=\"clamped-content-5e6d10f8978a453b2b31e346\"]");
        By addFriesBy = By.xpath("(//*[@class=\"sc-gqjmRU iTpbJv item_item__9DjGQ sc-dxgOiQ eCgKdX\"])[1]");
        By addBeefPattyBy = By.xpath("(//*[@class=\"sc-gqjmRU iTpbJv item_item__9DjGQ sc-dxgOiQ eCgKdX\"])[9]");
        By addToCartBy = By.xpath("//*[@class=\"button_btn__B71x7 button_block__1-IVQ button_block__1-IVQ button_base__WiB4z sc-htoDjs JzBiV\"]");
        By viewCartBy = By.xpath("(//*[@class=\"icon_icon__34QeS\"])[2]");
        By orderSummaryBy = By.xpath("//*[@class=\"orderSummary_price-container__voFrE\"]");

        // Adding a special burger
        var specialItemButton = driver.findElement(specialItemBy);
        specialItemButton.click();

        // Adding add-ons
        fluentWait.until(x->x.findElement(addFriesBy).isDisplayed());
        var addFriesButton = driver.findElement(addFriesBy);
        addFriesButton.click();

        var addBeefPattyButton = driver.findElement(addBeefPattyBy);
        addBeefPattyButton.click();

        // Adding special burger to cart
        var addToCartButton = driver.findElement(addToCartBy);
        addToCartButton.click();

        // Viewing cart
        fluentWait.until(x->x.findElement(viewCartBy).isDisplayed());
        var viewCartButton = driver.findElement(viewCartBy);
        viewCartButton.click();

        // Verifying cart summary

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(orderSummaryBy));
//        fluentWait.until(x->x.findElement(orderSummaryBy).isDisplayed());
        var cartElement = driver.findElements(orderSummaryBy);
//
        Assert.assertTrue(cartElement.size()==1);
    }

    // Test to verify the user can order alcohol
    @Test
    public void addAlcoholToCart() throws InterruptedException {

        // Web elements Xpath for alcohol items and view cart elements
        By beerBy = By.xpath("//*[@id=\"#Beer-nav\"]");
        By wineBy = By.xpath("//*[@id=\"#Wine-nav\"]");
        By spiritBy = By.xpath("//*[@id=\"#Spirits-nav\"]");

        By baladinBy = By.xpath("//*[text()='Baladin Isaac Bottle']");
        By lunaNudaBy = By.xpath("//*[text()='Luna Nuda Prosecco']");
        By chivasBy = By.xpath("//*[text()='Chivas 12 Year Scotch']");

        By verifyYourAgeBy = By.xpath("//*[@class=\"sc-gqjmRU iTpbJv item_item__9DjGQ sc-dxgOiQ eCgKdX\"]");
        By addToCartBy = By.xpath("//*[@class=\"button_btn__B71x7 button_block__1-IVQ button_block__1-IVQ button_base__WiB4z sc-htoDjs JzBiV\"]");
        By viewCartBy = By.xpath("(//*[@class=\"icon_icon__34QeS\"])[2]");
        By cartElementBy = By.xpath("//*[@class=\"cart_cart-content--items__323-K\"]");

        // Navigating to Beer section and adding a bottle of beer
        var beerButton = driver.findElement(beerBy);
        beerButton.click();

        var baladinButton = driver.findElement(baladinBy);
        baladinButton.click();

        // Verifying user's age and adding beer to cart
        fluentWait.until(x->x.findElement(verifyYourAgeBy).isDisplayed());
        var verifyYourAgeButton = driver.findElement(verifyYourAgeBy);
        var addToCartButton = driver.findElement(addToCartBy);
        verifyYourAgeButton.click();
        addToCartButton.click();

        // Navigating to Beer section and adding a bottle of wine
        var wineButton = driver.findElement(wineBy);
        wineButton.click();

        var lunaNudaButton = driver.findElement(lunaNudaBy);
        lunaNudaButton.click();

        // Verifying user's age and adding wine to cart
        fluentWait.until(x->x.findElement(verifyYourAgeBy).isDisplayed());
        var verifyYourAgeButton2 = driver.findElement(verifyYourAgeBy);
        var addToCartButton2 = driver.findElement(addToCartBy);
        verifyYourAgeButton2.click();
        addToCartButton2.click();

        // Navigating to Beer section and adding a spirit
        var spiritsButton = driver.findElement(spiritBy);
        spiritsButton.click();

        WebElement chivasButton = driver.findElement(chivasBy);
        chivasButton.click();

        // Verifying user's age and adding spirit to cart
        fluentWait.until(x->x.findElement(verifyYourAgeBy).isDisplayed());
        var verifyYourAgeButton3 = driver.findElement(verifyYourAgeBy);
        var addToCartButton3 = driver.findElement(addToCartBy);
        verifyYourAgeButton3.click();
        addToCartButton3.click();

        // View cart
        var viewCartButton = driver.findElement(viewCartBy);
        viewCartButton.click();
        Thread.sleep(2000);

        // Verify cart summary
        fluentWait.until(x->x.findElement(cartElementBy).isDisplayed());
        var cartElement = driver.findElements(cartElementBy);

        Assert.assertTrue(cartElement.size()==1);
    }
}
