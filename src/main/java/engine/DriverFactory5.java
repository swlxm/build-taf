package engine;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory5 {

    public static WebDriver getWebDriver(String name) {
        switch (name.toLowerCase()) {
            case "edge" -> {
                return new EdgeDriverCreator().getDriver();
            }
            case "firefox" -> {
                return new FirefoxDriverCreator().getDriver();
            }
            case "safari" -> {
                return new SafariDriverCreator().getDriver();
            }
            default -> {
                return new ChromeDriverCreator().getDriver();
            }
        }
    }
}
