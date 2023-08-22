package engine;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class EdgeDriverCreator implements DriverCreator {

    @Override
    public WebDriver getDriver(){
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
}
