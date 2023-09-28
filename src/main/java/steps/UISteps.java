package steps;

import com.github.javafaker.Faker;
import engine.DriverEnumWithMap;
import entities.Attribute;
import exceptions.NoSuchVariableException;
import exceptions.ZeroNumberLengthException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.WebBasePage;
import utils.GlobalVariables;
import utils.VariableUtil;

import java.util.concurrent.TimeUnit;

import static engine.GlobalVar.GLOBAL_VARIABLES;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class UISteps {

    private final WebBasePage webBasePage = new WebBasePage();
    private final Faker faker = new Faker();

    /**
     * open website
     *
     * @param url website url
     */
    @Given("open {url}")
    public void open(String url) throws InterruptedException, NoSuchVariableException {
        webBasePage.openWebURL(VariableUtil.replace(url));
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * click element on page, the naming formatter is Page_Name::Element_Name; the Page_Name should keep same with the page class name in
     * which define the elements, the Element_name should keep same with the field name and uppercase first letter. for example there is a
     * field in LoginPage.class with name loginBtn, the element should be defined as LoginPage::LoginBtn
     *
     * @param element Page_Name::Element_Name
     */
    @And("click {element}")
    public void clickElement(WebElement element) {
        webBasePage.clickElement(element);
    }

    /**
     * click element on page by xpath, using index to indicate the element if multiple elements are found
     *
     * @param xpath xpath string to identify the elements
     * @param index a digit number starts with 1, it will indicate position of element if there are multiple
     *              elements with the same xpath returned
     */
    @And("click {elementIdentifiedByXPath} with index {index}")
    public void clickElementByIndex(String xpath, int index) {
        webBasePage.getElements(By.xpath(xpath)).get(index - 1).click();
    }

    /**
     * input text into element
     *
     * @param text    a free string
     * @param element Page_Name::Element_Name
     */
    @And("input {text} into {element}")
    public void inputText(String text, WebElement element) throws NoSuchVariableException {
        webBasePage.inputText(element, VariableUtil.replace(text));
    }

    /**
     * a checkpoint to check if element is visible on page
     *
     * @param element Page_Name::Element_Name
     */
    @Then("CHECK {element} visibility")
    public void verifyElementVisible(WebElement element) {
        assertThat(webBasePage.isElementVisible(element, 20)).isTrue();
    }

    /**
     * a checkpoint to check a hyperlink with specific linked text
     *
     * @param text a string of linked text, only valid for link element
     */
    @Then("CHECK hyperlink with text {text} visibility")
    public void verifyLinkWithTextVisible(String text) {
//        assertThat(webBasePage.getElement(By.xpath("//a[contains(text(), '" + VariableUtil.validateVariable(text) + "')]"), WaitDuration.IMPLICIT.value())).isNotNull();
    }

    /**
     * a checkpoint to check a hyperlink with specific href attribute
     *
     * @param url a string of href attribute of link element
     */
    @Then("CHECK hyperlink with url {url} visibility")
    public void verifyLinkWithHrefVisible(String url) {
        assertThat(webBasePage.getElement(By.xpath("//a[contains(@href, '" + url + "')]"), 20)).isNotNull();
    }

    /**
     * a checkpoint to check the specific test displayed on page
     *
     * @param text a string of expected text
     */
    @Then("CHECK text {text} on page")
    public void checkpointTextIsVisible(String text) {
        assertThat(webBasePage.isElementVisible(By.xpath("//*[contains(text(), '" + text + "')]"))).isTrue();
    }

    /**
     * get the text of element and store it as a variable
     *
     * @param element  Page_Name::Element_Name
     * @param variable a string of variable name
     */
    @And("get {element} text and save as variable {variable}")
    public void getElementText(WebElement element, String variable) {
        String val = webBasePage.getElementText(element);
//        GLOBAL_VARIABLES.put(variable, val);
    }

    /**
     * Get text of the element which is identified by xpath, using index (1 means first element) if multiple elements are identified, and
     * store the text as a variable, it can be invoked with ${variable} in other steps
     *
     * @param xpath    the string of xpath
     * @param index    a digit number starts with 1, it will indicate position of element if there are multiple
     * @param variable variable name to store the text
     */
    @And("get {elementIdentifiedByXPath} with index {index} text and save as variable {variable}")
    public void getElementText(String xpath, int index, String variable) {
        String val = webBasePage.getElementText(xpath, index);
//        GLOBAL_VARIABLES.put(variable, val);
    }

    /**
     * get attribute value of element and store it as a variable
     *
     * @param attribute href|id|name|src|class
     * @param element   Page_Name::Element_Name
     * @param variable  variable name
     */
    @And("save {attribute} value of {element} as variable {variable}")
    public void getElementAttributeVal(Attribute attribute, WebElement element, String variable) {
        String val = webBasePage.getElementAttribute(element, attribute.getAttr());
//        GLOBAL_VARIABLES.put(VariableUtil.validateVariable(variable), val);
    }

    @And("close the browser")
    public void closeTheBrowser() {
        DriverEnumWithMap.INSTANCE.closeDriver();
    }

    /**
     * define a variable, it can be used with ${variable} in other steps
     *
     * @param variable a string of variable name
     * @param value    a string of real value
     */
    @Given("def {variable}={text}")
    public void defineVariable(String variable, String value) throws NoSuchVariableException, ZeroNumberLengthException {
        value = VariableUtil.replace(value);
        if (value.equalsIgnoreCase("_random")) {
            value = faker.name().fullName().replace(" ", "-").replace("'", "-");
        }
        GlobalVariables.INSTANCE.setVariable(variable, value);
    }

    @And("refresh page")
    public void refreshPage() {
        webBasePage.pageRefresh();
    }

    @And("go backward page")
    public void backPage() {
        webBasePage.pageBack();
    }

    @And("go forward page")
    public void forwardPage() {
        webBasePage.pageForward();
    }

}
