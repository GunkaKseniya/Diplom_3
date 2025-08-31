package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {

    private final SelenideElement logoutButton = $x("//button[text()='Выход']");
    private final SelenideElement constructorButton = $x("//p[text()='Конструктор']");
    private final SelenideElement profileHeading = $x("//a[contains(text(),'Профиль')]");
    private final SelenideElement logoLink = $x("//div[contains(@class,'AppHeader_header__logo')]/a");


    @Step("Нажатие кнопки Выход")
    public MainPage clickLogoutButton() {
        logoutButton.shouldBe(visible).click();
        return new MainPage();
    }

    @Step("Переход в Конструктор")
    public MainPage clickConstructorButton() {
        constructorButton.shouldBe(visible).click();
        return new MainPage();
    }

    @Step("Отображение личного кабинета")
    public boolean isProfileOpen() {
        return profileHeading.shouldBe(visible).isDisplayed();
    }

    @Step("Нажатие на лого")
    public MainPage clickStellarBurgersLogo() {
        logoLink.shouldBe(visible).click();
        return new MainPage();
    }
}
