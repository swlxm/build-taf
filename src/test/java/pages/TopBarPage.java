/*
 * Copyright © 2021 EPAM Systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class TopBarPage extends WebBasePage {

    @Getter
    @FindBy(how = How.XPATH, using = "//span[contains(text(),'New repository')]")
    private WebElement newRepoBtn;

    @Getter
    @FindBy(how = How.LINK_TEXT, using = "Your repositories")
    private WebElement repoListBtn;

    @Getter
    @FindBy(how = How.ID, using = "global-create-menu-button")
    private WebElement createNewDropDown;

    @Getter
    @FindBy(how = How.XPATH, using = "//summary[@aria-label='View profile and more']")
    private WebElement viewProfileDropDown;

    public TopBarPage() {
        super.pageName = "TopBar Page";
    }

    public void clickNewRepoBtn() {
        clickElement(createNewDropDown);
        clickElement(newRepoBtn);
    }

    public void clickRepoListBtn() {
        clickElement(viewProfileDropDown);
        clickElement(repoListBtn);
    }

}