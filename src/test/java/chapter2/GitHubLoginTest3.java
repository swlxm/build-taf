package chapter2;

import engine.DriverFactory2;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.LoginPageAdv;
import pages.MainPage;
import pages.MainPageAdv;

import java.time.Duration;

public class GitHubLoginTest3 {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = DriverFactory2.getWebDriver(System.getProperty("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void testLogin() {
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
