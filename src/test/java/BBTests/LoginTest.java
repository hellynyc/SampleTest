package BBTests;

import BBlisteners.RetryAnalyzer;
import BBPageObjects.LoginPage;
import BBPageObjects.MainPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    // Parameters for correct login credentials
    @Parameters({"email", "password"})
    // Test to verify successful login with correct credentials
    // You can enable/disable tests here, and include retryAnalyzer
    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class)
    public void LoginTest_CorrectCredentials_LoggedToBareburger(String loginEmail, String pw) {

        // Create new loginPage var
        // Login using function created in LoginPage, and create a mainPage var
        // Check to see if login is successful with function created in MainPage
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = loginPage.loginToBareburger(loginEmail, pw);
        Assert.assertTrue(mainPage.isMain());
    }

    // Parameters for wrong password
    @Parameters({"email", "wrong_password"})
    // Test to verify unsuccessful login with wrong password
    // You can enable/disable tests here, and include retryAnalyzer
    @Test(enabled = true, retryAnalyzer = RetryAnalyzer.class)
    public void LoginTest_WrongPassword_LoggedToBareburger(String loginEmail, String wrongPW) {

        // Create new loginPage var
        // Login using function created in LoginPage
        // Check to see if login has failed with function created in LoginPage
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToBareburger(loginEmail, wrongPW);
        Assert.assertTrue(loginPage.isError());
    }
}
