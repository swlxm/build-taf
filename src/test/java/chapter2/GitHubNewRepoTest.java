package chapter2;

import engine.DriverFactory5;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;

import java.time.Duration;

public class GitHubNewRepoTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = DriverFactory5.getWebDriver(System.getProperty("browser"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void testNewRepoCreation() {
        LoginPage loginPage = new LoginPage(driver);
        driver.get("https://www.github.com");
        new MainPage(driver).getSignInLnk().click();
        loginPage.getUsernameFld().sendKeys("your account");
        loginPage.getPasswordFld().sendKeys("your password");
        loginPage.getSignInBtn().click();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
