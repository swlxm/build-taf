/*
 * Copyright © 2021 EPAM Systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.assertj.core.api.Assertions.assertThat;

public class NewRepositoryPage extends WebBasePage {

    @Getter
    @FindBy(how = How.XPATH, using = "//input[@aria-label='Repository']")
    private WebElement repoNameFld;

    @Getter
    @FindBy(how = How.NAME, using = "Description")
    private WebElement repoDescFld;

    @Getter
    @FindBy(how = How.XPATH, using = "//input[@name='visibilityGroup' and @value='public']")
    private WebElement publicRadioBtn;

    @Getter
    @FindBy(how = How.XPATH, using = "//input[@name='visibilityGroup' and @value='private']")
    private WebElement privateRadioBtn;

    @Getter
    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Create repository')]")
    private WebElement createRepoBtn;

    @Getter
    @FindBy(how = How.XPATH, using = "//a[contains(@href,'repositories/new')][1]")
    private WebElement newRepoBtn;

    @Getter
    @FindBy(how = How.XPATH, using = "//summary[@aria-label='Create new…']")
    private WebElement createNewDropDown;

    private String repoName = null;

    public NewRepositoryPage() {
        super.pageName = "New Repo Page";
    }

    public void setRepoDesc(String repositoryDesc) {
        inputText(repoDescFld, repositoryDesc);
    }

    public void setRepoName(String repositoryName) {
        inputText(repoNameFld, repositoryName);
        this.repoName = repositoryName;
    }

    public void setPermission(String permission) {
        if ("PUBLIC".equalsIgnoreCase(permission)) {
            clickElement(publicRadioBtn);
        } else {
            clickElement(privateRadioBtn);
        }
    }

    public void clickCreateRepository() {
        waitUntilElementClickable(createRepoBtn);
        clickElement(createRepoBtn);
    }

    public void clickNewRepoBtn() {
        clickElement(newRepoBtn);
    }

    public void verifyNewRepo() {
        WebElement repo = getElement(By.xpath("//a[contains(text(), '" + repoName + "')]"));
        getElement(repo);
        assertThat(repo.isDisplayed()).isTrue();
    }

    public void setOrganization(String name) {

    }

}