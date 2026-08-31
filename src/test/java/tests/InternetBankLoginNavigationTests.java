package tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.PageObject;
import static com.codeborne.selenide.Selenide.switchTo;
import static io.qameta.allure.Allure.step;

@Story("Internet Bank Login Navigation")
public class InternetBankLoginNavigationTests extends TestBase {
    PageObject pageObject = new PageObject();

    //переход на страницу входа в интернет-банк по кнопке "Войти"
    @Test
    @DisplayName("Clicling Login button redirects to Online Banking login page")
    @Tag("Critical")
    void shouldOpenInternetBankLoginPage() {

        step("Open Web Main", () -> {
            pageObject.openWebMain();
        });

        step("Click on 'Login' button in header", () -> {
            pageObject.clickLoginButton();
        });

       step("Click on 'Интернет-банк ФЛ' button", () -> {
            pageObject.clickInternetBankFl();
        });

        //переход на новую вкладку входа в интернет-банк
        step("Switch to the new browser tab with login form", () -> {
            switchTo().window(1);
        });

        step("Verify that internet bank login page is successfully displayed", () -> {
            pageObject.verifyInternetBankLoginPageIsOpened();
        });
    }
}

