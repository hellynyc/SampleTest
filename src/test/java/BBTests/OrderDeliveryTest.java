package BBTests;

import BBPageObjects.LoginPage;
import BBPageObjects.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderDeliveryTest extends BaseTest {

    @Test
    public void deliveryTest_orderDelivery_deliveryOrdered() {
        By loginBy = By.xpath("//*[@alt=\"bareburger Icon\"]");
        By enterEmailBy = By.xpath("//*[@placeholder=\"Enter Email Here\"]");

        var loginButton = driver.findElement(loginBy);
        loginButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(enterEmailBy));
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = loginPage.loginToBareburger("thirsteacafe+test@gmail.com", "Lunchbox123");
        mainPage.deliveryOrder();
        Assert.assertTrue(mainPage.checkDeliveryOrder());
    }
}
