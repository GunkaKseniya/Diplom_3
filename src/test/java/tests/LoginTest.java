package tests;

import user.UserApiClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegisterPage;
import pageobject.ResetPasswordPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    private final String baseUrl = "https://stellarburgers.nomoreparties.site/";
    private final UserApiClient userApiClient = new UserApiClient();

    private String testEmail;
    private final String testPassword = "123456";
    private String accessToken;

    @Before
    public void setUp() {
        testEmail = "gunya_" + System.currentTimeMillis() + "@yandex.ru";
        accessToken = userApiClient.register(testEmail, testPassword);
        open(baseUrl);
    }

    @After
    public void tearDown() {
        closeWebDriver();
        if (accessToken != null) {
            userApiClient.deleteUser(accessToken);
        }
    }

    @Test
    public void loginByLoginButtonOnMainPage() {
        loginFromMainPageAndLogin(testEmail, testPassword);
    }

    @Test
    public void loginByPersonalAccountButton() {
        loginFromPersonalAccountAndLogin(testEmail, testPassword);
    }

    @Test
    public void loginByRegisterPageLink() {
        loginFromRegisterPageAndLogin(testEmail, testPassword);
    }

    @Test
    public void loginByResetPasswordPageLink() {
        loginFromResetPasswordPageAndLogin(testEmail, testPassword);
    }

    private void loginFromMainPageAndLogin(String email, String password) {
        MainPage mainPage = new MainPage();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage();
        loginPage.loginUser(email, password);

        loginSuccessful();
    }

    private void loginFromPersonalAccountAndLogin(String email, String password) {
        MainPage mainPage = new MainPage();
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage();
        loginPage.loginUser(email, password);

        loginSuccessful();
    }

    private void loginFromRegisterPageAndLogin(String email, String password) {
        MainPage mainPage = new MainPage();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage();
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage();
        registerPage.clickLoginLink();

        LoginPage loginPageAgain = new LoginPage();
        loginPageAgain.loginUser(email, password);

        loginSuccessful();
    }

    private void loginFromResetPasswordPageAndLogin(String email, String password) {
        MainPage mainPage = new MainPage();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage();
        loginPage.clickForgotPasswordLink();

        ResetPasswordPage resetPasswordPage = new ResetPasswordPage();
        resetPasswordPage.clickLoginLink();

        LoginPage loginPageAgain = new LoginPage();
        loginPageAgain.loginUser(email, password);

        loginSuccessful();
    }

    private void loginSuccessful() {
        assertTrue("Отображение конструктора после авторизации", new MainPage().isConstructorVisible());
    }
}

