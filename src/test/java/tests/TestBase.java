package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.WebConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Map;

public class TestBase {

    private static final WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());

    static {
        Configuration.baseUrl = System.getProperty("testSiteBaseUrl", "https://www.bspb.ru");
        if (!Configuration.baseUrl.endsWith("/")) {
            Configuration.baseUrl += "/";
        }
    }

    @BeforeAll
    static void setupSelenideConfig() {
        Configuration.timeout = 10000;
        Configuration.baseUrl = webConfig.getBaseUrl();
        Configuration.browser = webConfig.getBrowser();
        Configuration.browserVersion = webConfig.getBrowserVersion();
        Configuration.browserSize = webConfig.getBrowserSize();

        // Удалённый запуск с видео
        String remoteUrl = System.getProperty("remoteBrowserUrl", webConfig.getRemoteUrl());
        if (remoteUrl != null && !remoteUrl.isEmpty()) {
            Configuration.remote = String.format("https://%s:%s@%s",
                    System.getProperty("remoteBrowserUrlLogin", "user1"),
                    System.getProperty("remoteBrowserUrlPassword", "1234"),
                    remoteUrl);

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true,
                    "screenResolution", System.getProperty("browserResolution", "1920x1080") + "x24"));
            Configuration.browserCapabilities = capabilities;
        } else {
            Configuration.remote = webConfig.getRemoteUrl();
        }
    }

    @BeforeEach
    void init() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void tearDown() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            // Сохраняем sessionId, ПОКА драйвер жив
            String sessionId = null;
            try {
                sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver())
                        .getSessionId().toString();
            } catch (Exception ignored) {
            }

            try {
                Attach.screenshotAs("Last screenshot");
                Attach.pageSource();
                Attach.browserConsoleLogs();
            } catch (Exception e) {
                System.err.println("Не удалось сохранить вложения Allure: " + e.getMessage());
            } finally {
                Selenide.closeWebDriver();
            }

            // Видео прикрепляем ПОСЛЕ закрытия, но с сохранённым sessionId
            if (sessionId != null) {
                Attach.addVideo(sessionId);
            }
        }
    }
}
