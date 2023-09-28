package chapter2.single_map;

import chapter2.singleton.GitHubTestBase;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.LoginPageAdv;
import pages.MainPage;
import pages.MainPageAdv;

public class GitHubLoginTest extends GitHubTestBase {

    @Test
    public void testLogin() {
//        Person person = new Person("firstName", "lastName");
//        Person person = new Person("firstName", "lastName", 18);
//        Person person = Person.builder().firstName("firstName").build();
//        Person person = Person.builder().firstName("firstName").age(18).build();
//        Person person = Person.builder().firstName("firstName").address("address").build();
        LoginPage loginPage = new LoginPage(driver);
        driver.get("https://www.github.com");
        new MainPage(driver).getSignInLnk().click();
        loginPage.getUsernameFld().sendKeys("your account");
        loginPage.getPasswordFld().sendKeys("your password");
        loginPage.getSignInBtn().click();
    }

}
