package pageobject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class MainPage {

    private final SelenideElement loginButton = $x("//button[contains(text(),'Войти в аккаунт')]");
    private final SelenideElement constructorButton = $("p.AppHeader_header__linkText__3q_va.ml-2");
    private final SelenideElement constructorHeader = $("h1");
    private final SelenideElement personalAccountButton = $x("//p[contains(@class,'AppHeader_header__linkText__3q_va') and contains(@class,'ml-2') and (text()='Личный Кабинет')]");
    private final SelenideElement logoButton = $("div.AppHeader_header__logo__2D0X2");
    private final SelenideElement orderButton = $x("//button[text()='Оформить заказ']");
    public final ElementsCollection tabs = $$("div.tab_tab__1SPyG");

    @Step("Нажатие кнопки Войти")
    public LoginPage clickLoginButton() {
        loginButton.shouldBe(visible).click();
        $x("//button[text()='Войти']").shouldBe(visible, Duration.ofSeconds(5));
        return new LoginPage();
    }

    @Step("Нажатие на лого")
    public MainPage clickLogo() {
        logoButton.shouldBe(visible).click();
        return this;
    }

    @Step("Нажатие кнопки Конструктор")
    public MainPage clickConstructorButton() {
        constructorButton.shouldHave(text("Конструктор")).shouldBe(visible).click();
        return this;
    }

    @Step("Нажатие кнопки Личный кабинет")
    public MainPage clickPersonalAccountButton() {
        personalAccountButton.shouldBe(visible).click();
        return this;
    }

    @Step("Нажатие вкладки с прокруткой")
    public MainPage clickTab(String tabName) {
        SelenideElement tab = tabs.findBy(text(tabName)).shouldBe(visible);
        tab.scrollTo();
        tab.shouldBe(visible, enabled);

        executeJavaScript("arguments[0].click();", tab);

        // ожидание для корректного переключения вкладок
        tabs.findBy(cssClass("tab_tab_type_current__2BEPc"))
                .shouldHave(text(tabName), Duration.ofSeconds(3))
                .shouldBe(visible, Duration.ofSeconds(5));

        return this;
    }

    @Step("Получение текста текущей вкладки")
    public String getCurrentTabText() {
        return tabs.findBy(cssClass("tab_tab_type_current__2BEPc")).shouldBe(visible).getText();
    }

    @Step("Отображение конструктора")
    public boolean isConstructorVisible() {
        constructorHeader.shouldBe(visible);
        return constructorHeader.getText().contains("Соберите бургер");
    }

    @Step("Отображение авторизационной формы")
    public boolean isLoginPageVisible() {
        return $("form").shouldBe(visible).exists();
    }

    @Step("Отображение кнопки Оформить заказ")
    public boolean isOrderButtonVisible() {
        return orderButton.shouldBe(visible, Duration.ofSeconds(6)).isDisplayed();
    }
}
