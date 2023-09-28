package steps;

import entities.Attribute;
import io.cucumber.java.ParameterType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import pages.WebBasePage;

import java.lang.reflect.InvocationTargetException;

public class ParameterTypeUtil {

    private final WebBasePage basePage = new WebBasePage();

    @ParameterType(".*")
    public String response(String response) {
        return response;
    }

    @ParameterType(".*")
    public String module(String module) {
        return module;
    }

    @ParameterType(".*")
    public String caseNo(String caseNo) {
        return caseNo;
    }

    @ParameterType(".*")
    public String caseDescription(String desc) {
        return desc;
    }

    @ParameterType(".*")
    public String headers(String headers) {
        return headers;
    }

    @ParameterType(".*")
    public String parameters(String params) {
        return params;
    }

    @ParameterType(".*")
    public String payloadFilePath(String filePath) {
        return filePath;
    }

    @ParameterType("\\d{3}")
    public int code(String code) {
        return Integer.parseInt(code);
    }

    @ParameterType(".*")
    public String url(String url) {
        return url;
    }

    @ParameterType(".*")
    public String elementIdentifiedByXPath(String xpath) {
        return xpath;
    }

    @ParameterType(".*")
    public String value(String value) {
        return value;
    }

    @ParameterType("[a-zA-Z]+")
    public String elementName(String elementName) {
        return elementName;
    }

    @ParameterType("[a-zA-Z]+")
    public String pageName(String pageName) {
        return pageName;
    }

    @ParameterType(".*")
    public String text(String text) {
        return text;
    }

    @ParameterType(".*")
    public String variable(String varName) {
        return varName;
    }

    @ParameterType("\\d{1}")
    public int index(String index) {
        return Integer.parseInt(index);
    }

    @ParameterType("\\d{1,4}")
    public int duration(String duration) {
        return Integer.parseInt(duration);
    }

    @ParameterType("\\d{1}")
    public int times(String times) {
        return Integer.parseInt(times);
    }

    @ParameterType("href|id|name|src|class")
    public Attribute attribute(String attribute) {
        return new Attribute(attribute);
    }

    @ParameterType("([a-zA-Z]+)::([a-zA-Z]+)")
    public WebElement element(String pageName, String elementName)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return getElement(pageName, elementName);
    }

    @ParameterType("\\d{1,4},\\d{1,4})")
    public Point point(String x, String y) {
        return new Point(Integer.parseInt(x), Integer.parseInt(y));
    }

    @ParameterType(".*")
    public String jsonpath(String jsonpath) {
        return jsonpath;
    }

    @ParameterType(".*")
    public String jsonValue(String jsonValue) {return jsonValue;}

    private WebElement getElement(String pageName, String elementName)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        basePage.setPageName(pageName);
        Class<?> clazz = Class.forName("pages." + pageName);
        java.lang.reflect.Method method = clazz.getDeclaredMethod("get" + elementName);
        return (WebElement) method.invoke(clazz.getDeclaredConstructor().newInstance());
    }
}
