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

    static {
        Configuration.baseUrl = System.getProperty("testSiteBaseUrl", "https://www.bspb.ru");
        if (!Configuration.baseUrl.endsWith("/")) {
            Configuration.baseUrl += "/";
        }
    }

    @BeforeEach
        //добавляет скриншоты
    void init() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.pageLoadStrategy = "eager";
    }

    @BeforeAll
    static void setup() {
        //меняем на edge для запуска локально и проверки pdf-файла (строки 36-37 вкл., 35, 51-57 выкл.)
        //+закачали файл msedgedriver.exe
        Configuration.browser = System.getProperty("browser", "chrome");
//        Configuration.browser = System.getProperty("browser", "edge");
//        System.setProperty("webdriver.edge.driver", "./msedgedriver.exe");

        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        Configuration.browserSize = System.getProperty("browserResolution", "1920x1080");
        Configuration.baseUrl = System.getProperty("testSiteBaseUrl", "https://www.bspb.ru/");
        Configuration.timeout = 4000;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = "https://" +
                System.getProperty("remoteBrowserUrlLogin", "user1") +
                ":" +
                System.getProperty("remoteBrowserUrlPassword", "1234") +
                "@" +
                System.getProperty("remoteBrowserUrl", "selenoid.autotests.cloud/wd/hub");
}

        @AfterEach
        void tearDown () {
            // существует ли веб-драйвер в текущем потоке
            if (WebDriverRunner.hasWebDriverStarted()) {
                try {
                    //добавляем вложения Allure только при живом драйвере
                    Attach.screenshotAs("Last screenshot");
                    Attach.pageSource();
                    Attach.browserConsoleLogs();
                } catch (Exception e) {
                    System.err.println("Не удалось сохранить вложения Allure: " + e.getMessage());
                } finally {
                    Selenide.closeWebDriver();
                }
            }
        }
    }

