package engine;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SimpleDriverFactory
{
    public static WebDriver getWebDriver(String name) {
        switch (name.toLowerCase()) {
            case "edge" -> {
                return new EdgeDriver();
            }
            case "firefox" -> {
                return new FirefoxDriver();
            }
            default -> {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(initChromeDriver());
            }
        }
    }

    private static ChromeOptions initChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox"); //Bypass OS security model
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1200");
        options.addArguments("--remote-allow-origins=*");
        options.setHeadless(true);
        return options;
    }
}
