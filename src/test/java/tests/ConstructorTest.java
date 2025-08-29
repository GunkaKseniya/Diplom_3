package tests;

import org.junit.Test;
import pageobject.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class ConstructorTest extends BaseTest {

    private final String baseUrl = "https://stellarburgers.nomoreparties.site/";

    @Test
    public void tabSwitch() {
        open(baseUrl);
        MainPage mainPage = new MainPage();

        mainPage.clickTab("Булки");
        assertEquals("Булки", mainPage.getCurrentTabText());

        mainPage.clickTab("Соусы");
        assertEquals("Соусы", mainPage.getCurrentTabText());

        mainPage.clickTab("Начинки");
        assertEquals("Начинки", mainPage.getCurrentTabText());
    }

    @Test
    public void returnToConstructorByLogo() {
        open(baseUrl);
        MainPage mainPage = new MainPage();
        mainPage.clickLoginButton();
        mainPage.clickLogo();
        assertTrue("Отображение конструктора после клика по лого", mainPage.isConstructorVisible());
    }
}
