package entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EnvEntity {

    public static final String BROWSER = "browser";
    public static final String ENV = "env";
    public static final String IS_GENERATE_FEATURE = "generateFeatures";
    public static final String LOCAL_ADDRESS = "local.ipAddress";
    public static final String LOCAL_APPIUM_PORT = "local.appiumPort";
    public static final String DRIVER_PATH = "driver.path";
    public static final String APPIUM_PATH = "appium.path";
    public static final String TEMPLATE_PATH = "template.path";
}
