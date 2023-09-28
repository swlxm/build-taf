package steps;/*
 * Copyright © 2021 EPAM Systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

import engine.DriverEnumWithMap;
import entities.EnvEntity;
import io.cucumber.java.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static engine.GlobalVar.GLOBAL_VARIABLES;

@Slf4j
public class BDDHook {

    @Before
    public void beforeScenario(Scenario scenario) {
        log.info("*** Start scenario: {} ***", scenario.getName());
    }


    @After
    public void afterScenario(Scenario scenario) {
        afterStep(scenario);
        log.info("*** End scenario: {} ***", scenario.getName());
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed() && !GLOBAL_VARIABLES.get("platform").equals("api")) {
            //Take screenshot to report
            final byte[] screen = ((TakesScreenshot) DriverEnumWithMap.INSTANCE.getDriver()).getScreenshotAs(OutputType.BYTES);

            scenario.attach(screen, "image/png", "Step Screenshot");
            //Take screenhshots to project folder
            saveScreenshotFile(scenario);
        }
    }

    private void saveScreenshotFile(Scenario scenario) {
        String screenshotName = scenario.getName().replaceAll(" ", "_");
        String methodName = screenshotName + "_" + getCurrentTimeAsString();

        TakesScreenshot driver = (TakesScreenshot) DriverEnumWithMap.INSTANCE.getDriver();
        File screenFile = driver.getScreenshotAs(OutputType.FILE);
        File file = new File(".//target/screenshots/" + methodName + ".png");
        try {
            FileUtils.copyFile(screenFile, file);
            log.info("Screenshot is saved successfully: {}", file.getAbsolutePath());
        } catch (IOException e) {
            log.error("Failed to save screenshot: {}", e.getLocalizedMessage());
        }
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }

}
