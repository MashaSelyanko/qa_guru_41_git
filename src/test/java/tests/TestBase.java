package tests;

import com.codeborne.selenide.Configuration;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    static void setupSelenideConfig() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://qa-guru.github.io";
        Configuration.timeout = 10000;          // default 4000


        //для записи видео. Это объект свойств chrome
        ChromeOptions options = new ChromeOptions();
        options.setCapability("selenoid:options", Map.of(
                "enableVNC", true,      //потоковое видео
                "enableVideo", true
        ));                                     //можно добавить сертификат и пр.настройки
        Configuration.browserCapabilities = options;
        //удаленная ферма
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();


        Attach.attachAsText("Some file", "Some content");
    }

    void tearDown() {
        closeWebDriver();
    }
}



