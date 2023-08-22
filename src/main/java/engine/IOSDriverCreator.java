package engine;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;

public class IOSDriverCreator implements DriverCreator {

    @Override
    public WebDriver getDriver() {

//        return new IOSDriver(DriverSingleton.getInstance().getAppiumService().getUrl(), new MobileCapabilites().getDeviceCapabilitiesFromPlataform("IOS"));
return  null;
    }
}
