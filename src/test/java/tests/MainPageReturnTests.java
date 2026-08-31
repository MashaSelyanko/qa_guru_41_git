package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.PageObject;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static com.codeborne.selenide.Selenide.open;

public class MainPageReturnTests extends TestBase {

    PageObject pageObject = new PageObject();

    //проверка перехода на главную страницу по логотипу
    @Test
    @Tag("Blocker")
    @DisplayName("Logo click redirects to home page")
    void openFeedbackForm() {

        step("Open feedback form", () -> {
            open("/retail/feedback/fl");
        });

        step("Check submenu Buttons", () -> {
            pageObject.clickHomeLogo();
        });

        step("Check main Page", () -> {
            pageObject.checkMainPageUrl();
        });

        step("Check promo banner", () -> {
            pageObject.checkPromoBannerIsVisible();
        });
    }
}