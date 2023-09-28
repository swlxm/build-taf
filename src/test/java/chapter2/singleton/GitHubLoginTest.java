package chapter2.singleton;

import org.testng.annotations.Test;
import pages.LoginPage;
import pages.LoginPageAdv;
import pages.MainPage;
import pages.MainPageAdv;

public class GitHubLoginTest extends GitHubTestBase {

    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        driver.get("https://www.github.com");
        new MainPage(driver).getSignInLnk().click();
        loginPage.getUsernameFld().sendKeys("your account");
        loginPage.getPasswordFld().sendKeys("your password");
        loginPage.getSignInBtn().click();
    }

}
