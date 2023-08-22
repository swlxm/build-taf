package engine;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory3 {

    private WebDriver driver;

    public static WebDriver getWebDriver(String name) {
        switch (name.toLowerCase()) {
            case "edge" -> {
                //EdgeOptions
                return new EdgeDriver();
            }
            case "firefox" -> {
                //FirefoxOptions
                return new FirefoxDriver();
            }
            default -> {
                System.setProperty("webdriver.http.factory", "jdk-http-client");
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox"); //Bypass OS security model
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1200");
                options.addArguments("--remote-allow-origins=*");
                options.setHeadless(true);
                return new ChromeDriver();
            }
        }
    }

}
