package engine;

import entities.EnvEntity;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.GlobalVariables;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.*;

@Slf4j
public enum DriverEnumWithMap {

    INSTANCE;

    private static WebDriver driver;

    private AppiumDriverLocalService service;

    public void startDriver() {
        if (null == driver){
            String browser = GlobalVariables.INSTANCE.getVariable(EnvEntity.BROWSER).toUpperCase();
            driver = DriverCreatorMapping.getDriverCreator(browser).getDriver();
            log.info("the current browser is {}", browser);
            if(!("ANDROID".equals(browser) || "IOS".equals(browser))) driver.manage().window().maximize();
        }
    }

    public WebDriver getDriver(){
        startDriver();
        return driver;
    }

    public AppiumDriverLocalService getAppiumService() {
        if (null == service) {
            startAppiumService();
        }
        return service;
    }

    public URL getUrl() {
        if (null == service) {
            startAppiumService();
        }
        return service.getUrl();
    }

    private void startAppiumService() {
        AtomicInteger port = new AtomicInteger();
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        AppiumDriverLocalService service = builder
                .usingAnyFreePort()
                .withIPAddress("127.0.0.1")
                .withArgument(SESSION_OVERRIDE)
                .withArgument(LOG_LEVEL, "error")
                .withArgument(BASEPATH, "/wd/hub/")
                .usingDriverExecutable(new File("/usr/local/bin/node"))
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .build();
        Optional.ofNullable(service).ifPresent(s -> {
            s.start();
            port.set(s.getUrl().getPort());
        });
        this.service = service;
        log.info("Starting appium service on port {}", port);
    }

    public void closeDriver(){
        if (null != driver) {
            driver.quit();
            driver = null;
            log.info("the driver quit");
        }
    }

}
