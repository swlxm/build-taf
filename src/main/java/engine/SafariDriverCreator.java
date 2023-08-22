package engine;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SafariDriverCreator implements DriverCreator {

    @Override
    public WebDriver getDriver(){
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        return new ChromeDriver(initChromeOptions());
    }

    private ChromeOptions initChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox"); //Bypass OS security model
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1200");
        options.addArguments("--remote-allow-origins=*");
        options.setHeadless(true);
        return options;
    }
}
