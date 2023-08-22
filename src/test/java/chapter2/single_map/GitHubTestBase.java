package chapter2.single_map;

import engine.DriverEnum;
import engine.DriverEnumWithMap;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class GitHubTestBase {

    protected static WebDriver driver;

    @BeforeSuite
    public void setUp() {
        driver = DriverEnumWithMap.INSTANCE.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterSuite
    public void tearDown() {
        driver.close();
    }
}
