package engine;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;

public class AndroidDriverCreator implements DriverCreator {
    @Override
    public WebDriver getDriver() {
//        return new AndroidDriver(DriverSingleton.getInstance().getAppiumService().getUrl()
//                ,new MobileCapabilites().getDeviceCapabilitiesFromPlataform("ANDROID"));
    return null;
    }
}
