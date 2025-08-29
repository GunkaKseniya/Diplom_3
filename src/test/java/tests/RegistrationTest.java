package tests;

import user.UserApiClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.MainPage;
import pageobject.RegisterPage;
import pageobject.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

public class RegistrationTest {

    private final String baseUrl = "https://stellarburgers.nomoreparties.site/";
    private final String name = "Тест";
    private String email;
    private final String shortPassword = "123";
    private final String validPassword = "Pa$$word123@";

    private final UserApiClient userApiClient = new UserApiClient();

    @Before
    public void setUp() {
        email = "gunya" + System.currentTimeMillis() + "@example.com";
        open(baseUrl);
    }

    @After
    public void tearDown() {
        userApiClient.deleteUser(email, validPassword);
    }

    @Test
    public void successfulRegistration() {
        MainPage mainPage = new MainPage();
        LoginPage loginPage = mainPage.clickLoginButton();

        RegisterPage registerPage = loginPage.clickRegisterLink();

        registerPage.registerNewUser(name, email, validPassword);

        loginPage = new LoginPage();
        loginPage.loginUser(email, validPassword);

        mainPage = new MainPage();
        assertTrue("Кнопка Оформить заказ не отображается", mainPage.isOrderButtonVisible());
    }

    @Test
    public void shortPasswordError() {
        String emailShortPass = "shortpass" + System.currentTimeMillis() + "@example.com";

        MainPage mainPage = new MainPage();
        LoginPage loginPage = mainPage.clickLoginButton();

        RegisterPage registerPage = loginPage.clickRegisterLink();

        registerPage.registerNewUser(name, emailShortPass, shortPassword);

        assertTrue("Ошибка короткого пароля не отображается", registerPage.isPasswordErrorVisible());
    }
}
