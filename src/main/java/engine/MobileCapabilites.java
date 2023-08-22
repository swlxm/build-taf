package engine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.TestDataReader;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;

public class MobileCapabilites {
    private static Logger logger = LoggerFactory.getLogger(MobileCapabilites.class);

    public DesiredCapabilities getDeviceCapabilitiesFromPlataform(String mavenEnvironment) {

        DesiredCapabilities desiredCapabilities = null;
        if (mavenEnvironment.contains("ANDROID")) {
            desiredCapabilities = Load_capabilities.pathToDesiredCapabilitites(TestDataReader.getEnvTestDataByKey("AndroidGemotionPath"));
            desiredCapabilities.setCapability("app", new File("app/ApiDemos-debug.apk").getAbsolutePath());
        } else if (mavenEnvironment.contains("IOS")) {
            desiredCapabilities = Load_capabilities.pathToDesiredCapabilitites(TestDataReader.getEnvTestDataByKey("IOSPath"));
            desiredCapabilities.setCapability("app", new File("app/TestApp.app").getAbsolutePath());
        }
        return desiredCapabilities;

    }

    static class Load_capabilities {
        private static Logger logger = LoggerFactory.getLogger(Load_capabilities.class);

        public static DesiredCapabilities pathToDesiredCapabilitites(String path) {
            try {
                Gson gson = new Gson();
                Type type = new TypeToken<Map<String, ?>>(){}.getType();
                Map<String , ?> map =  gson.fromJson(new FileReader(path), type);
                return new DesiredCapabilities(map);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            return null;
        }

    }




}
