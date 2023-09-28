/*
 * Copyright © 2022 EPAM Systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package chapter3;

import engine.DriverEnumWithMap;
import entities.EnvEntity;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.GlobalVariables;
import utils.PropertiesReader;

import java.time.Duration;

@CucumberOptions(
        glue = {"steps"},
        features = {"src/test/java/features"},
        tags = "@bdd_demo",
        plugin = {"pretty",
                "html:target/cucumber-report/index.html",
                "json:target/cucumber-report/cucumber.json"})
@Slf4j
public class GitHubLoginTest extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void setUp() {
        GlobalVariables.INSTANCE.setVariable(EnvEntity.ENV, System.getProperty(EnvEntity.ENV));
        GlobalVariables.INSTANCE.setVariable(EnvEntity.BROWSER, System.getProperty(EnvEntity.BROWSER));
        PropertiesReader.readPropertyFile();
        System.out.println(GlobalVariables.INSTANCE.getAsMap().toString());
        DriverEnumWithMap.INSTANCE.startDriver();
    }

    @AfterSuite
    public void tearDown() {
        DriverEnumWithMap.INSTANCE.closeDriver();
    }

}

