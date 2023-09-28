/*
 * Copyright © 2022 EPAM Systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositoryPage extends WebBasePage {

    @Getter
    @FindBy(how = How.ID, using = "your-repos-filter")
    private WebElement repoFilter;

    @Getter
    @FindBy(how = How.ID, using = "user-repositories-list")
    private WebElement searchSummary;

    @Getter
    @FindBy(how = How.ID, using = "settings-tab")
    private WebElement setting;

    @Getter
    @FindBy(how = How.XPATH, using = "//*[@aria-label='Delete repository']//input[@name='verify']")
    private WebElement verifyField;

    @Getter
    @FindBy(how = How.XPATH, using = "//summary[contains(text(), 'Delete this repository')]")
    private WebElement deleteRepo;

    @Getter
    @FindBy(how = How.XPATH, using = "//span[text()='I understand the consequences, delete this repository']")
    private WebElement submitBtn;

    @Getter
    @FindBy(how = How.XPATH, using = "//a[@itemprop='name codeRepository']")
    private List<WebElement> repoList;

    public RepositoryPage() {
        super.pageName = "Repo Page";
    }

    public void inputRepoFilter(String repoName) throws InterruptedException {
        inputText(repoFilter, repoName);
        TimeUnit.SECONDS.sleep(2);
    }

    public void verifyDeletedRepo() {
        assertThat(searchSummary.getText()).contains("0 results for repositories matching");
    }

}