package tests;

import user.UserApiClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.ProfilePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

public class ProfileTest {

    private final String baseUrl = "https://stellarburgers.nomoreparties.site/";
    private final UserApiClient userApiClient = new UserApiClient();

    private String testEmail;
    private final String testPassword = "123456";
    private String accessToken;

    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;

    @Before
    public void setUp() {
        testEmail = "gunya_" + System.currentTimeMillis() + "@yandex.ru";
        accessToken = userApiClient.register(testEmail, testPassword);

        mainPage = new MainPage();
        loginPage = new LoginPage();
        profilePage = new ProfilePage();
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userApiClient.deleteUser(accessToken);
        }
    }

    @Test
    public void followToProfile() {
        open(baseUrl);
        mainPage.clickLoginButton();
        loginPage.loginUser(testEmail, testPassword);
        mainPage.clickPersonalAccountButton();
        assertTrue(profilePage.isProfileOpen());
    }

    @Test
    public void logoutFromProfile() {
        open(baseUrl);
        mainPage.clickLoginButton();
        loginPage.loginUser(testEmail, testPassword);
        mainPage.clickPersonalAccountButton();
        profilePage.clickLogoutButton();
        assertTrue(loginPage.isLoginFormVisible());
    }

    @Test
    public void followFromProfileToConstructor() {
        open(baseUrl);
        mainPage.clickLoginButton();
        loginPage.loginUser(testEmail, testPassword);
        mainPage.clickPersonalAccountButton();
        profilePage.clickConstructorButton();
        assertTrue(mainPage.isConstructorVisible());
    }

    @Test
    public void followFromProfileByLogo() {
        open(baseUrl);
        mainPage.clickLoginButton();
        loginPage.loginUser(testEmail, testPassword);
        mainPage.clickPersonalAccountButton();
        profilePage.clickStellarBurgersLogo();
        assertTrue(mainPage.isConstructorVisible());
    }
}

