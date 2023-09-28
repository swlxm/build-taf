package utils;

import entities.EnvEntity;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class PropertiesReader {

    private static final Properties properties = new Properties();

    public static void readPropertyFile() {
        String env = GlobalVariables.INSTANCE.getVariable(EnvEntity.ENV);
        if (null == env) {
            env = "dev";
            log.warn("-Denv is not found in command, default value {} will be used.", env.toUpperCase());
        } else {
            log.info("Execution environment is {}", env.toUpperCase());
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/test/resources/" + getPlatform() + "_" + env + ".properties"))) {
            properties.load(bufferedReader);
            properties.forEach((k, v) -> GlobalVariables.INSTANCE.setVariable(k.toString(), v.toString()));
        } catch (IOException e) {
            log.debug("exception", e);
        }

    }

    private static String getPlatform() {
        String platform = GlobalVariables.INSTANCE.getVariable(EnvEntity.BROWSER);
        if (null == platform) {
            platform = "api";
            log.warn("-Dbrowser is not found in command, default value {} will be used.", platform.toUpperCase());
        } else {
            log.info("Execution platform is {}", platform.toUpperCase());
        }
        switch (platform.toUpperCase()) {
            case "CHROME", "FIREFOX", "IE", "EDGE", "SAFARI" -> {
                return "web";
            }
            default -> {
                log.error("Please define platform in maven command");
                throw new IllegalArgumentException("Please define [browser] in maven command with -Dbrowser");
            }
        }
    }
}
