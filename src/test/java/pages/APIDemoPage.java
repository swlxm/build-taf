package pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class APIDemoPage extends WebBasePage {

    public APIDemoPage() {
//        super.pageName = "API Demo Page";
    }

    @Getter
    @FindBy(how = How.XPATH, using = "//*[@text='Media']")
    private WebElement media;

    @Getter
    @FindBy(how = How.XPATH, using = "//*[@text='Views']")
    private WebElement views;

    @Getter
    @FindBy(how = How.XPATH, using = "//*[@text='WebView']")
    private WebElement webView;

    @Getter
    @FindBy(how = How.ID, using = "newproduct-search-icon")
    private WebElement login;
}
