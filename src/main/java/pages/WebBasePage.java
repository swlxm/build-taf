/*
 * Copyright © 2022 EPAM Systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package pages;

import engine.DriverEnumWithMap;
import engine.DriverSingleton;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.GlobalVariables;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class WebBasePage {

    protected static WebDriver driver;
    protected String pageName;

    public WebBasePage() {
        driver = DriverEnumWithMap.INSTANCE.getDriver();
        PageFactory.initElements(driver, this);
    }
    private static final String LOG_MSG = "Element on {} is visible in {} seconds\n{}";
    private static final String LOG_ERROR_MSG_NOT_VISIBLE = "Element is not visible after %s seconds.";
    private static final String LOG_ERROR_MSG_NOT_CLICKABLE = "Element is not clickable in %s seconds.";


    public void pageRefresh() {
        driver.navigate().refresh();
        waitForDOMReady();
    }

    public void pageForward() {
        driver.navigate().forward();
        waitForDOMReady();
    }

    public void pageBack() {
        driver.navigate().back();
        waitForDOMReady();
    }

    public void openWebURL(String url) {
        log.info("open website {}", url);
        driver.get(url);
        waitForDOMReady();
    }

    public LogEntries getBrowserLogs() {
        return driver.manage().logs().get("browser");
    }

    /**
     * PingID is required before login some systems, it will block the script execution, to avoid it we can use cookie to login directly, to
     * achieve this purpose we can save the cookie to local file first with auth cookie, then read the file and set new cookies with auth
     * info
     *
     * @param driver
     * @param cookieFilePath
     * @throws IOException
     */
    public void saveCookie(WebDriver driver, String cookieFilePath) throws IOException {
        List<String> cookies = new ArrayList<>();
        driver.manage().getCookies().forEach(cookie -> cookies.add(cookie.toString()));
        Path path = Path.of(cookieFilePath);
        try {
            Files.write(path, cookies, StandardOpenOption.CREATE_NEW);
        } catch (FileAlreadyExistsException ex) {
            Files.delete(path);
            Files.write(path, cookies, StandardOpenOption.CREATE_NEW);
        }

    }

    /**
     * openWebURL("http://www.baidu.com"); // commented the next three lines when the cookies are saved Thread.sleep(120 * 1000);
     * saveCookie(driver, "cookie.txt"); Thread.sleep(5 * 1000); // setCookie(driver, "cookie.txt"); pageRefresh();
     *
     * @param driver
     * @param cookieFilePath
     */
    public void setCookie(WebDriver driver, String cookieFilePath) {
        Path path = Path.of(cookieFilePath);
        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(line -> {
                String[] arr = line.split(";");
                String key = arr[0].split("=")[0].trim();
                String val = arr[0].split("=")[1].trim();
                driver.manage().addCookie(new Cookie(key, val));
            });
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

//    private <R> R defineStateWithoutTimeouts(Action<R> action) {
//        this.setTimeout(WaitDuration.IMPLICIT_LOW.value());
//
//        Object state;
//        try {
//            state = action.perform();
//        } finally {
//            this.setTimeout(WaitDuration.IMPLICIT.value());
//        }
//
//        return state;
//    }

    private void setTimeout(int timeOutOfSeconds) {
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((long)timeOutOfSeconds));
    }

    public void clickElement(WebElement element) {
        try {
            waitUntilElementClickable(element);
            element.click();
            log.info("Click element on {}\n{}", this.pageName, element);
        } catch (TimeoutException var3) {
            log.warn(var3.toString());
            ((JavascriptExecutor)this.driver).executeScript("arguments[0].click();", new Object[]{element});
        }

    }

    public void inputText(WebElement element, String text) {
        this.waitUntilElementVisible(element);
        element.clear();
        element.sendKeys(new CharSequence[]{text});
//        log.info("Input \"{}\" into element on {}\n{}", new Object[]{text, this.pageName, element});
    }

    public String getElementAttribute(WebElement element, String attribute) {
        this.waitUntilElementVisible(element);
        return element.getAttribute(attribute);
    }

    private boolean isDOMReady() {
        return ((JavascriptExecutor)this.driver).executeScript("return document.readyState", new Object[0]).equals("complete");
    }

    public void waitForDOMReady() {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(60));
        wait.until((input) -> {
            return this.isDOMReady();
        });
    }

    public WebElement getElement(WebElement element, int... timeOutOfSeconds) {
        try {
            this.waitUntilElementVisible(element, timeOutOfSeconds);
        } catch (TimeoutException var4) {
            log.error("exception {}", var4.toString());
        }

        return element;
    }

    public WebElement getElement(By locator, int... timeOutOfSeconds) {
        return this.waitUntilElementVisible(locator, timeOutOfSeconds);
    }

    public List<WebElement> getElements(By locator, int... timeOutOfSeconds) {
        return this.waitUntilElementsVisible(locator, timeOutOfSeconds);
    }

    public boolean isElementVisible(WebElement element, int... timeOutOfSeconds) {
        try {
            this.waitUntilElementVisible(element, timeOutOfSeconds);
            return true;
        } catch (TimeoutException var4) {
            log.error("exception {}", var4.toString());
            return false;
        }
    }

    public boolean isElementVisible(By by, int... timeOutOfSeconds) {
        return this.waitUntilElementVisible(by, timeOutOfSeconds).isDisplayed();
    }

    public String getElementText(WebElement element) {
        return element.getText();
    }

    public String getElementText(String xpath, int index) {
        return ((WebElement)this.getElements(By.xpath(xpath)).get(index - 1)).getText();
    }

    public void waitUntilElementClickable(WebElement element, int... timeOutOfSeconds) throws TimeoutException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(GlobalVariables.INSTANCE.getVariable("lowerImplicitWait"))));
        int waitDuration = Integer.parseInt(GlobalVariables.INSTANCE.getVariable("lowerImplicitWait"));
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(waitDuration));
        WebElement e = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(element));
        if (null == e) {
            throw new TimeoutException(String.format("Element is not clickable in %s seconds." + element, waitDuration));
        } else {
//            log.info("Element on {} is visible in {} seconds\n{}", new Object[]{this.pageName, waitDuration, e});
        }
    }

    public WebElement waitUntilElementClickable(By by, int... timeOutOfSeconds) throws TimeoutException {
        int waitDuration = 20;
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds((long)waitDuration));
        WebElement element = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(by));
        if (null == element) {
            throw new TimeoutException(String.format("Element is not clickable in %s seconds." + by, waitDuration));
        } else {
//            log.info("Element on {} is visible in {} seconds\n{}", new Object[]{this.pageName, waitDuration, element});
            return element;
        }
    }

    public void waitUntilElementVisible(WebElement element, int... timeOutOfSeconds) throws TimeoutException {
        int waitDuration = 20;
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds((long)waitDuration));
        WebElement e = (WebElement)wait.until(ExpectedConditions.visibilityOf(element));
        if (null == e) {
            throw new TimeoutException(String.format("Element is not visible after %s seconds." + element.toString(), waitDuration));
        } else {
//            log.info("Element on {} is visible in {} seconds\n{}", new Object[]{this.pageName, waitDuration, e});
        }
    }

    public WebElement waitUntilElementVisible(By by, int... timeOutOfSeconds) throws TimeoutException {
        int waitDuration = 20;
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds((long)waitDuration));
        WebElement e = (WebElement)wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        if (null == e) {
            throw new TimeoutException(String.format("Element is not visible after %s seconds." + by.toString(), waitDuration));
        } else {
//            log.info("Element on {} is visible in {} seconds\n{}", new Object[]{this.pageName, waitDuration, e});
            return e;
        }
    }

    public List<WebElement> waitUntilElementsVisible(By by, int... timeOutOfSeconds) throws TimeoutException {
        int waitDuration = 20;
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds((long)waitDuration));
        List<WebElement> elements = (List)wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        if (elements.isEmpty()) {
            throw new TimeoutException(String.format("Element is not visible after %s seconds." + by.toString(), waitDuration));
        } else {
//            log.info("Element on {} is visible in {} seconds\n{}", new Object[]{this.pageName, waitDuration, elements});
            return elements;
        }
    }

    public boolean waitUntilElementDisappeared(WebElement element, int... timeOutOfSeconds) {
        int count = 20;
        boolean visible = true;

        while(true) {
            try {
                if (!element.isDisplayed()) {
                    visible = false;
                    log.info("{} disappears in {} second(s)", element, count);
                    break;
                }

                Thread.sleep(5);
            } catch (InterruptedException var6) {
                log.error("exception {}", var6.getMessage());
            }

            if (count-- == 0) {
                log.error("{} is still exists in {} second(s)", element, 20);
                break;
            }
        }

        return visible;
    }

    public List<String> getTextsFromElements(List<WebElement> webElements) {
        return (List)webElements.stream().map((element) -> {
            return StringUtils.split(element.getText(), "");
        }).flatMap(Arrays::stream).filter(StringUtils::isNotEmpty).collect(Collectors.toList());
    }

}