package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class RegisterPage {

    private final SelenideElement registerLink = $x("//a[text()='Зарегистрироваться']");
    private final SelenideElement registerButton = $x("//button[text()='Зарегистрироваться']");
    private final SelenideElement loginLink = $x("//a[text()='Войти']");
    private final SelenideElement nameField = $x("//label[text()='Имя']/following-sibling::input");
    private final SelenideElement emailField = $x("//label[text()='Email']/following-sibling::input");
    private final SelenideElement passwordField = $x("//label[text()='Пароль']/..//input");
    private final SelenideElement passwordError = $x("//p[contains(text(),'Некорректный пароль')]");

    @Step("Переход на страницу регистрации")
    public RegisterPage clickRegisterLink() {
        registerLink.shouldBe(visible).click();
        return this;
    }

    @Step("Ввод имени")
    public RegisterPage enterName(String name) {
        nameField.shouldBe(visible).setValue(name);
        return this;
    }

    @Step("Ввод email")
    public RegisterPage enterEmail(String email) {
        emailField.shouldBe(visible).setValue(email);
        return this;
    }

    @Step("Ввод пароля")
    public RegisterPage enterPassword(String password) {
        passwordField.shouldBe(visible).setValue(password);
        return this;
    }

    @Step("Нажатие кнопки Зарегистрироваться")
    public RegisterPage clickRegisterButton() {
        registerButton.shouldBe(visible).click();
        return this;
    }

    @Step("Переход по ссылке Войти")
    public LoginPage clickLoginLink() {
        loginLink.shouldBe(visible).click();
        return new LoginPage();
    }

    @Step("Отображение ошибки о некорректном пароле")
    public boolean isPasswordErrorVisible() {
        return passwordError.exists() && passwordError.isDisplayed();
    }

    @Step("Регистрация нового пользователя")
    public RegisterPage registerNewUser(String name, String email, String password) {
        return enterName(name)
                .enterEmail(email)
                .enterPassword(password)
                .clickRegisterButton();
    }

    @Step("Поле Имя отображается")
    public boolean isNameInputVisible() {
        return nameField.exists() && nameField.isDisplayed();
    }
}
