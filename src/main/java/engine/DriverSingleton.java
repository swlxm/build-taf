package engine;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DriverSingleton {
    private static final Logger logger = LoggerFactory.getLogger(DriverSingleton.class);

    private static volatile DriverSingleton INSTANCE;

    private static WebDriver driver;

    private DriverSingleton(){}

    public static DriverSingleton getInstance() {
        if (null == INSTANCE) {
            synchronized (DriverSingleton.class) {
                if (null == INSTANCE) {
                    INSTANCE = new DriverSingleton();
                }
            }
        }
        return INSTANCE;
    }

    private void startDriver() {
        if (null == driver){
            String browser = System.getProperty("browser").toLowerCase();
            logger.info("Test browser is: {}", browser);
            driver = DriverCreatorMapping.getDriverCreator(browser.toUpperCase()).getDriver();
            logger.info("the current browser is " + browser);
            if(!("ANDROID".equals(browser) || "IOS".equals(browser))) driver.manage().window().maximize();
        }
    }

//    private void startDriver() {
//        DriverCreator driverCreator;
//        if (null == driver){
//            String browser = System.getProperty("browser").toLowerCase();
//            logger.info("Test browser is: {}", browser);
//            switch (browser) {
//                case "edge" -> {
//                    driverCreator = new EdgeDriverCreator();
//                }
//                case "firefox" -> {
//                    driverCreator = new FirefoxDriverCreator();
//                }
//                default -> {
//                    driverCreator = new ChromeDriverCreator();
//                }
//            }
//            driver = driverCreator.getDriver();
//            logger.info("the current browser is " + browser);
//            if(!("ANDROID".equals(browser) || "IOS".equals(browser))) driver.manage().window().maximize();
//        }
//    }
//
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
//    public void startAppiumService() {
//        service = AppiumDriverLocalService.buildDefaultService();
//        service.start();
//        logger.info("the appium service started");
//    }
//
//    public AppiumDriverLocalService getAppiumService() {
//        return service;
//    }
//
//    public void stopAppiumService() {
//        if (null != service) service.stop();
//        logger.info("the appium service was stopped");
//    }
