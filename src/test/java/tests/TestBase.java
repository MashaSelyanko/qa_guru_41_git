package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.Map;

public class TestBase {

    @BeforeEach
    void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        // драйвер запускается открытием базового URL или пустой страницы
        Selenide.open("");
    }

    @BeforeAll
    static void setupEnvironment() {
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "128.0");
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        Configuration.browserSize = System.getProperty("browserResolution", "1920x1080");
        Configuration.baseUrl = System.getProperty("testSiteBaseUrl", "https://demoqa.com");
        Configuration.timeout = 4000;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = "https://" +
                System.getProperty("remoteBrowserUrlLogin", "user1") + // второе значение - по умолчанию
                ":" +
                System.getProperty("remoteBrowserUrlPassword") +
                "@" +
                System.getProperty("remoteBrowserUrl", "selenoid.autotests.cloud/wd/hub");
    }

    @AfterEach
    void addAttachments() {
        // существует ли веб-драйвер в текущем потоке
        if (WebDriverRunner.hasWebDriverStarted()) {
                //добавляем вложения Allure только при живом драйвере
                Attach.screenshotAs("Last screenshot");
                Attach.pageSource();
                Attach.browserConsoleLogs();

                // закрываем веб-драйвер после каждого теста
                Selenide.closeWebDriver();
            }
        }
    }




