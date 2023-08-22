package engine;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DriverCreatorMapping {
    private static final Map<String, DriverCreator> MAP = new HashMap<>();

    static {
        MAP.put("CHROME", new ChromeDriverCreator());
        MAP.put("EDGE", new EdgeDriverCreator());
        MAP.put("FIREFOX", new FirefoxDriverCreator());
    }

    public static DriverCreator getDriverCreator(String browser) {
        return Optional.ofNullable(MAP.get(browser.toUpperCase())).orElseThrow(() ->
                new IllegalArgumentException("Illegal argument " + browser + ", please check -Dbrowser, only CHROME|EDGE|FIREFOX supported."));
    }
}

//    public static DriverCreator getDriverCreator(String browser) {
//        DriverCreator driverCreator = MAP.get(browser.toUpperCase());
//        if (null == driverCreator) {
//            throw new IllegalArgumentException("Illegal argument " + browser + ", please check -Dbrowser, only CHROME|EDGE|FIREFOX supported.");
//        } else {
//            return driverCreator;
//        }
//    }
