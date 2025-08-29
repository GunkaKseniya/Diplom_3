package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ResetPasswordPage {

    private final SelenideElement emailInput = $("input[name='name']");
    private final SelenideElement restoreButton = $x("//button[text()='Восстановить']");
    private final SelenideElement loginLink = $x("//a[text()='Войти']");

    @Step("Ввод email")
    public ResetPasswordPage enterEmail(String email) {
        emailInput.shouldBe(visible).setValue(email);
        return this;
    }

    @Step("Нажатие кнопки Восстановить")
    public ResetPasswordPage clickRestoreButton() {
        restoreButton.shouldBe(visible).click();
        return this;
    }

    @Step("Нажатие ссылки Войти")
    public LoginPage clickLoginLink() {
        loginLink.shouldBe(visible).click();
        return new LoginPage();
    }

    @Step("Восстановление пароля по email")
    public ResetPasswordPage restorePassword(String email) {
        return enterEmail(email)
                .clickRestoreButton();
    }
}

