package engine;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.TestDataReader;

import java.io.File;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.MobileCapabilityType.*;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

public class AndroidDriverCreator implements DriverCreator {
    @Override
    public WebDriver getDriver() {
        return new AndroidDriver(DriverEnumWithMap.INSTANCE.getUrl()
                , initCapabilities());
    }

    private Capabilities initCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(APP_PACKAGE, "io.appium.android.apis");
        capabilities.setCapability(APP_ACTIVITY, "io.appium.android.apis.ApiDemos");
        capabilities.setCapability(DEVICE_NAME, "d7605f46");
        capabilities.setCapability(PLATFORM_NAME, "android");
        capabilities.setCapability(PLATFORM_VERSION, "11");
        capabilities.setCapability(AUTOMATION_NAME, "UiAutomator2");
        return capabilities;
    }
}
