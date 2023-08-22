package chapter2.single_map;

import chapter2.singleton.GitHubTestBase;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;

public class GitHubNewRepoTest extends GitHubTestBase {

    @Test
    public void testNewRepoCreation() {
        LoginPage loginPage = new LoginPage(driver);
        driver.get("https://www.github.com");
        new MainPage(driver).getSignInLnk().click();
        loginPage.getUsernameFld().sendKeys("your account");
        loginPage.getPasswordFld().sendKeys("your password");
        loginPage.getSignInBtn().click();
    }

}
