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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Arrays;
import java.util.HashMap;
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
        Configuration.timeout = 20000; // Увеличим общий таймаут
        Configuration.baseUrl = "https://www.bspb.ru/";
        Configuration.browserSize = "1920x1080";
        Configuration.remote = "http://127.0.0.1:4444";
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // ✅ Chrome-флаги для стабильности в Docker
        Map<String, Object> chromeOptions = new HashMap<>();
        chromeOptions.put("args", Arrays.asList(
                "--no-sandbox",                    // ← Критично для Docker
                "--disable-dev-shm-usage",         // ← Уже было
                "--disable-gpu",                   // ← Стабильнее в WSL2
                "--disable-software-rasterizer",
                "--window-size=1920,1080",
                "--proxy-bypass-list=<-loopback>"
        ));
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        // ✅ Selenoid-опции
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", false,
                "screenResolution", "1920x1080"
        ));

        Configuration.browserCapabilities = capabilities;
        System.out.println("✅ Using local Selenoid at http://localhost:4444");
    }
//    static void setupSelenideConfig() {
//        Configuration.timeout = 10000;
//        Configuration.baseUrl = webConfig.getBaseUrl();
//        Configuration.browser = webConfig.getBrowser();
//        Configuration.browserVersion = webConfig.getBrowserVersion();
//        Configuration.browserSize = webConfig.getBrowserSize();
//
//        // Видео и VNC — включены по умолчанию (Selenide сам обработает, где это возможно)
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("selenoid:options", Map.of(
//                "enableVNC", true,
//                "enableVideo", true,
//                "screenResolution", System.getProperty("browserResolution", "1920x1080")  // ✅ Убрали "x24"
//        ));
//        Configuration.browserCapabilities = capabilities;
//
//        // Удалённый запуск — только если явно передан URL
//        String remoteUrl = System.getProperty("remoteBrowserUrl");
//        if (remoteUrl != null && !remoteUrl.isEmpty()) {
//            String login = System.getProperty("remoteBrowserUrlLogin", "user1");
//            String password = System.getProperty("remoteBrowserUrlPassword", "1234");
//
//            // Нормализуем URL: убираем протокол, если есть
//            String host = remoteUrl.replaceAll("^https?://", "");
//            Configuration.remote = "https://" + login + ":" + password + "@" + host;
//
//            System.out.println("🌐 Remote: " + Configuration.remote);
//        } else {
//            // Локальный запуск — remote не устанавливаем
//            Configuration.remote = null;
//            System.out.println("🏠 Local browser");
//        }
//    }

    @BeforeEach
    void init() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void tearDown() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            // ✅ Видео ДО закрытия (sessionId ещё активен)
            //Attach.addVideo();  -- УБРАТЬ КОММЕНТ

            // Остальные вложения
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();

            // Закрываем браузер
            Selenide.closeWebDriver();
        }
    }
}
