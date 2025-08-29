package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    private final SelenideElement emailField = $("form.Auth_form__3qKeq input[type='text']");
    private final SelenideElement passwordField = $("form.Auth_form__3qKeq input[type='password']");
    private final SelenideElement loginButton = $("button.button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_medium__3zxIa");
    private final SelenideElement registerLink = $("a.Auth_link__1fOlj[href='/register']");
    private final SelenideElement forgotPasswordLink = $("a.Auth_link__1fOlj[href='/forgot-password']");

    @Step("Ввод почты")
    public LoginPage enterEmail(String email) {
        emailField.shouldBe(visible).setValue(email);
        return this;
    }

    @Step("Ввод пароля")
    public LoginPage enterPassword(String password) {
        passwordField.shouldBe(visible).setValue(password);
        return this;
    }

    @Step("Нажатие кнопки Войти")
    public LoginPage clickLoginButton() {
        loginButton.shouldBe(enabled);
        try {
            loginButton.click();
        } catch (Exception e) {
            executeJavaScript("arguments[0].click();", loginButton);
        }
        return this;
    }

    @Step("Переход по ссылке Зарегистрироваться")
    public RegisterPage clickRegisterLink() {
        registerLink.shouldBe(visible).click();
        return new RegisterPage();
    }

    @Step("Переход по ссылке Восстановить пароль")
    public ResetPasswordPage clickForgotPasswordLink() {
        forgotPasswordLink.shouldBe(visible).click();
        return new ResetPasswordPage();
    }

    @Step("Форма входа отображается")
    public boolean isLoginFormVisible() {
        return loginButton.shouldBe(visible).exists();
    }

    @Step("Авторизация пользователя")
    public void loginUser(String email, String password) {
        enterEmail(email)
                .enterPassword(password)
                .clickLoginButton();
    }
}

