package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.MainPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class NavigationTest {

    private MainPage mainPage;

    @Before
    public void setUp() {
        open("https://stellarburgers.nomoreparties.site/");
        mainPage = new MainPage();
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }

    @Test
    public void bunsTabIsActive() {
        assertEquals("Булки", mainPage.getCurrentTabText());
    }

    @Test
    public void saucesTabIsActive() {
        mainPage.clickTab("Соусы");
        mainPage.tabs.findBy(cssClass("tab_tab_type_current__2BEPc"))
                .shouldHave(text("Соусы"), Duration.ofSeconds(2));
        assertEquals("Соусы", mainPage.getCurrentTabText());
    }

    @Test
    public void fillingsTabIsActive() {
        mainPage.clickTab("Начинки");
        mainPage.tabs.findBy(cssClass("tab_tab_type_current__2BEPc"))
                .shouldHave(text("Начинки"), Duration.ofSeconds(2));
        assertEquals("Начинки", mainPage.getCurrentTabText());
    }
}

