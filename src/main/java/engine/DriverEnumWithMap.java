package engine;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum DriverEnumWithMap {

    INSTANCE;

    private static WebDriver driver;

    private static final Logger logger = LoggerFactory.getLogger(DriverSingleton.class);

    private void startDriver() {
        DriverCreator driverCreator;
        if (null == driver){
            String browser = System.getProperty("browser").toUpperCase();
            driver = DriverCreatorMapping.getDriverCreator(browser).getDriver();
            logger.info("the current browser is " + browser);
            if(!("ANDROID".equals(browser) || "IOS".equals(browser))) driver.manage().window().maximize();
        }
    }

    public WebDriver getDriver(){
        startDriver();
        return driver;
    }

    public void closeDriver(){
        if (null != driver) {
            driver.quit();
            driver = null;
            logger.info("the driver quit");
        }
    }

}
