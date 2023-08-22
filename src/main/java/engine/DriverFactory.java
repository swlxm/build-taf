package engine;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private WebDriver driver;

    public static WebDriver getWebDriver(String name) {
        switch (name.toLowerCase()) {
            case "edge" -> {
                return new EdgeDriver();
            }
            case "firefox" -> {
                return new FirefoxDriver();
            }
            default -> {
                System.setProperty("webdriver.http.factory", "jdk-http-client");
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
                return new ChromeDriver();
            }
        }
    }

}
