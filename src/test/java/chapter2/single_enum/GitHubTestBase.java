package chapter2.single_enum;

import engine.DriverEnum;
import engine.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class GitHubTestBase {

    protected static WebDriver driver;

    @BeforeSuite
    public void setUp() {
        driver = DriverEnum.INSTANCE.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterSuite
    public void tearDown() {
        driver.close();
    }
}
