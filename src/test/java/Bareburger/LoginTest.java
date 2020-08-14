package Bareburger;

import BareburgerPageObjects.LoginPage;
import BareburgerPageObjects.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    // Test to verify successful login with correct credentials
    @Test
    public void LoginTest_CorrectCredentials_LoggedToBareburger() {

        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = loginPage.loginToBareburger("thirsteacafe+test@gmail.com", "Lunchbox123");
        Assert.assertTrue(mainPage.isMain());
    }

    // Test to verify unsuccessful login with wrong password
    @Test
    public void LoginTest_WrongPassword_LoggedToBareburger() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToBareburger("thirsteacafe+test@gmail.com", "123456");
        Assert.assertTrue(loginPage.isError());
    }
}
