package pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import tests.CustomerCategories;
import java.time.Duration;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class TextBoxPage {


    private final ElementsCollection categoryLinks = $$("div.css-1ndprbh a.chakra-link");
    private final ElementsCollection submenuButtons = $$("#header-menu-submenu_height nav button");

    private SelenideElement userfullNameInput = $("[name^='FIRSTNAME']");
    private SelenideElement userBirthInput = $("[name^='COMMENT']");
    private SelenideElement userNumberInput = $("[name^='PHONENUMBER']");
    private SelenideElement userEmailInput = $("[name^='EMAIL']");

    private SelenideElement submitButton = $(byText("Продолжить"));
    private SelenideElement HomeLogoButton = $(".chakra-link[href='/']");

    private final SelenideElement promoBanner = $(".chakra-ui-light .css-8cbovm");

    //Actions
    @Step("Open Web Main")
    public void openWebMain() {
        open("");
    }

    @Step("Check header contains expected category")
    //выбираем категорию
    public void selectCategory(CustomerCategories category) {
        categoryLinks.filterBy(Condition.partialText(category.typeName))
                .first()
                .click();
        com.codeborne.selenide.Selenide.sleep(1000);
    }

    //ждем, что в подменю появится хотя бы одна кнопка выбранной категории
    @Step("Validate submenu buttons")
    public void checkSubmenuButtons() {
        submenuButtons.shouldHave(CollectionCondition.sizeGreaterThan(0));

        //все кнопки подменю отображаются на экране
        submenuButtons.forEach(button -> button.shouldBe(Condition.visible));
    }

    //заполняем поле ФИО кириллицей
    @Step("Type full Name \"{value}\"")
    public void typefullNameInput(String value) {
        userfullNameInput.setValue(value);
    }

    //заполняем поле ФИО латиницей
    @Step("Type full Name \"{value}\"")
    public void typefullNameInputNegative(String value) {
        userfullNameInput.setValue(value);
    }

    @Step("Type email \"{value}\"")
    public void typeUserEmail(String value) {
        userEmailInput.setValue(value);
    }

    //заполняем поле Дата рождения
    @Step("Type date of birth {day} {month} {year}")
    public void typeBirthInput(String value) {
        userBirthInput.setValue(value);
    }

    //заполняем поле Номер телефона
    @Step("Type telefon number {number}")
    public void setUserTelNumberInput(String number) {
        userNumberInput.setValue(number);
    }

    @Step("Submit form")
    public void submitForm() {
        submitButton.click();
    }

    //клик по кнопке "Войти" в хэдере, ожидание выпадающего меню
    @Step("Click the 'Login' button in header")
    public void clickLoginButton() {
        com.codeborne.selenide.Selenide.
                $(com.codeborne.selenide.Selectors.byText("Войти")).click();
        $(byText("Интернет-банк ФЛ")).shouldBe(Condition.visible, Duration.ofSeconds(8)).click();
    }

    // внутри родительского выпадающего меню ищем текст+клик
    @Step("Click InternetBankFl")
    public void clickInternetBankFl() {
        com.codeborne.selenide.Selenide
                .$$(com.codeborne.selenide.Selectors.byText("Интернет-банк ФЛ"))
                .get(0)
                .shouldBe(com.codeborne.selenide.Condition.visible, java.time.Duration.ofSeconds(5))
                .click();
    }

    //клик по логотипу
    @Step("Click Home logo")
    public void clickHomeLogo() {
        com.codeborne.selenide.Selenide.executeJavaScript("arguments[0].click();", HomeLogoButton);
            }

    //Tests

    //проверка перехода на "Шаг 2 из 4"
    @Step("Verify that Step 2 of 4 is successfully opened")
    public void verifyStepTwoIsOpened() {
        // Находим h4 внутри блока с id и проверяем, что он просто содержит этот текст
        $("[id='StepInfo Форма обратной связи'] h4")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Шаг 2 из 4"), java.time.Duration.ofSeconds(5));
    }

    //проверка, что есть сообщение об ошибке
    @Step("Check negative registration form results")
    public void verifyRussianLettersErrorIsVisible() {
        $(byText("Доступны только русские буквы"))
                .shouldBe(Condition.visible, Duration.ofSeconds(5));
    }

    //проверка перехода на страницу входа в интернет-банк
    @Step("Verify 'Вход в интернет-банк'")
    public void verifyInternetBankLoginPageIsOpened() {
        com.codeborne.selenide.Selenide
                .$(com.codeborne.selenide.Selectors.byText("Вход в интернет-банк"))
                .shouldBe(Condition.visible, java.time.Duration.ofSeconds(45))
                .click();
    }

    //проверка, что на форме есть баннер главной страницы
    @Step("Check that promo banner is visible")
    public void checkPromoBannerIsVisible() {
        promoBanner.shouldBe(Condition.visible);
    }

    //  //проверка, что текущий URL совпадает с базовым
    @Step("Check that main page URL is opened")
    public void checkMainPageUrl() {
        webdriver().shouldHave(url(Configuration.baseUrl),Duration.ofSeconds(10));
    }

}




