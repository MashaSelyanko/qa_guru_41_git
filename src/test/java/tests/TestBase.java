package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }                      //добавляет скриншоты
    // лучше здесь, чем в BeforeAll, т.к. иначе подстветка будет только у первого теста

    @BeforeAll
    static void setupSelenideConfig () {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://qa-guru.github.io";
        Configuration.timeout = 10000; // default 4000
     Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }



    @AfterEach
    void tearDown () {
        closeWebDriver();
    }
}


