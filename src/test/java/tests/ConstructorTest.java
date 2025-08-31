package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.MainPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    private final String baseUrl = "https://stellarburgers.nomoreparties.site/";
    private MainPage mainPage;

    @Before
    public void setUp() {
        open(baseUrl);
        mainPage = new MainPage();
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }

    @Test
    public void tabSwitch() {
        checkTab("Булки");
        checkTab("Соусы");
        checkTab("Начинки");
    }

    private void checkTab(String tabName) {
        mainPage.clickTab(tabName);
        mainPage.tabs.findBy(text(tabName))
                .shouldBe(visible)
                .shouldHave(cssClass("tab_tab_type_current__2BEPc"), Duration.ofSeconds(3));
    }

    @Test
    public void returnToConstructorByLogo() {
        mainPage.clickLoginButton();
        mainPage.clickLogo();
        assertTrue("Отображение конструктора после клика по лого", mainPage.isConstructorVisible());
    }

    @Test
    public void defaultBunsTabIsActive() {
        mainPage.tabs.findBy(text("Булки"))
                .shouldHave(cssClass("tab_tab_type_current__2BEPc"), Duration.ofSeconds(2));
    }
}
