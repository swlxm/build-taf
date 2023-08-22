package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class TestDataReader {

    private final List<String> list = new LinkedList<>();
    private static final Logger logger = LoggerFactory.getLogger(TestDataReader.class);

    public static String getEnvTestDataByKey(String key) {
        Properties prpt = new Properties();
        String env = System.getProperty("environment");
        logger.info("Test environment is: {}", env);
        try {
            switch (env){
                case("QA"):{
                    BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/test/resources/qa.properties"));
                    prpt.load(bufferedReader);
                    break;
                }
                default:{
                    BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/test/resources/dev.properties"));
                    prpt.load(bufferedReader);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return prpt.getProperty(key);
    }
}
