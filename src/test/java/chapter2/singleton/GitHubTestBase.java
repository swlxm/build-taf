package chapter2.singleton;

import engine.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class GitHubTestBase {

    protected static WebDriver driver;

    @BeforeSuite
    public void setUp() {
        driver = DriverSingleton.getInstance().getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterSuite
    public void tearDown() {
        driver.close();
    }
}
