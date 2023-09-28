package engine;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.TestDataReader;

import java.io.File;

import static io.appium.java_client.remote.IOSMobileCapabilityType.BUNDLE_ID;
import static io.appium.java_client.remote.MobileCapabilityType.*;
import static io.appium.java_client.remote.MobileCapabilityType.UDID;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

public class IOSDriverCreator implements DriverCreator {

    @Override
    public WebDriver getDriver() {

        return new IOSDriver(DriverEnumWithMap.INSTANCE.getUrl(),
                initCapabilities());
    }

    private Capabilities initCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(BUNDLE_ID, "org.reactjs.native.example.DemoApp");
        capabilities.setCapability(DEVICE_NAME, "iPhone 14 Pro");
        capabilities.setCapability(PLATFORM_NAME, "ios");
        capabilities.setCapability(PLATFORM_VERSION, "16.1");
        capabilities.setCapability(AUTOMATION_NAME, "XCUITest");
        capabilities.setCapability(UDID, "7A99E8AE-0089-4173-B9CD-2E51A21BF890");
        capabilities.setCapability("xcodeOrgId", "6MN6P9GS3Z");
        capabilities.setCapability("xcodeSigningId", "iPhone Developer");
        return capabilities;
    }
}
