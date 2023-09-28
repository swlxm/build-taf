package chapter2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class GitHubLoginTest2 {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
//        WebDriverManager.edgedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void testLogin() {
        driver.get("https://www.github.com");
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("login_field")).sendKeys("your account");
        driver.findElement(By.id("password")).sendKeys("your password");
        driver.findElement(By.name("commit")).click();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
