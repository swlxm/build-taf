/*
 * Copyright © 2021 EPAM Systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    public MainPage(WebDriver driver) {
        super();
    }

    @Getter
    @FindBy(how = How.LINK_TEXT, using = "Sign in")
    private WebElement signInLnk;

    @Getter
    @FindBy(how = How.XPATH, using = "//summary[@aria-label='View profile and more']")
    private WebElement userProfileImg;

//    public void clickSignIn() {
//        clickElement(signInLnk);
//        getElement(signInBtn);
//        assertThat(signInBtn.isDisplayed()).isTrue();
//    }
//
//    public void setAccount(String username, String password) {
//        getElement(passwordFld);
//        inputText(usernameFld, username);
//        inputText(passwordFld, password);
//        clickElement(signInBtn);
//    }
//
//    public void verifySignedIn() {
//        assertThat(userProfileImg.isDisplayed()).as("The user profile image displayed").isTrue();
//    }

//    public void clickChineseLink() {
//        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//        WebDriverWait wait = new WebDriverWait(driver, 30);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/footer/div/div[2]/ul/div[1]/div/div[2]/span[1]/a/span")));
//        clickElement(driver, linkChinese);
//        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
//        log.info("I select the language for women page as Chinese");
//    }
//
//    public void clickEnglishLink() {
//        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
//        WebDriverWait wait = new WebDriverWait(driver, 30);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/footer/div/div[2]/ul/div[1]/div/div[2]/span[3]")));
//        clickElement(driver, linkEnglish);
//        log.info("I select the language as english");
//    }
//
//    public void checkAfterSwitchLanguage(String language) {
//        Assert.assertTrue(checkText.isDisplayed());
//        Assert.assertEquals(checkText.getText(), language);
//        log.info("* After switch language, check the header of the text successfully");
//    }
//
//    public void navigateToWomen() {
//        clickElement(driver, womenLink);
//        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
//        log.info("I navigate to the women page");
//    }
//
//    public void clickMyAccount() {
////        waitUntilElementClickable(driver,btnMyAccount);
////        btnMyAccount.click();
//        clickElement(driver, btnMyAccount);
//    }
//
//    public void hoverMyAccountandClickRegister() {
//        moveToElementAndClickElement(driver, btnMyAccount, btnRegister);
//    }
//
//    public void hoverWomenMenuandClickNewArrivals() {
//        moveToElementAndClickElement(driver, btnWomen, lnkWomenNewArrival);
//    }
//
//    public void hoverMyAccount() {
//        moveToElement(driver, btnMyAccount);
//    }
//
//    public void moveToLogo() {
//        moveToElement(driver, barLogo);
//    }
//
//    public boolean isProductinUserGuestMenu(String product_id) {
//        String productInGuestMenuXpath = "//div[@class='user-guest-menu']//a[contains(@href,'" + product_id + "')]";
//        int size = driver.findElements(By.xpath(productInGuestMenuXpath)).size();
//        if (size > 0) {
//            return true;
//        }
//        return false;
//    }
//
//    public void hoverBagMenuandCliekNewArrivals() throws InterruptedException {
//        moveToElementAndClickElement(driver, bagMenu, linkLoaBagArrival);
//    }
//
//    public void chooseScarfUnderWoman() {
//        moveToElementAndClickElement(driver, btnWomen, sacrfBagArrival);
//    }

}