package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class MainPageReturnTests extends TestBase {

    TextBoxPage textBoxPage = new TextBoxPage();

    //проверка перехода на главную страницу по логотипу
    @Test
    @Tag("Blocker")
    @DisplayName("Logo click redirects to home page")
    void openFeedbackForm() {

        step("Open feedback form", () -> {
            textBoxPage.openFeedbackForm();
        });

        step("Check submenu Buttons", () -> {
            textBoxPage.clickHomeLogo();
        });

        step("Check main Page", () -> {
            textBoxPage.checkMainPageUrl();
        });

        step("Check promo banner", () -> {
            textBoxPage.checkPromoBannerIsVisible();
        });
    }
}