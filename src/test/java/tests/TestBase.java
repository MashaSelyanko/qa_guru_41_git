package tests;

import com.codeborne.selenide.Configuration;
import config.WebConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class TestBase {

    private static final WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());

    @BeforeAll
    static void setupSelenideConfig () {
        //Configuration.browser = WebDriverProvider.class.getName();
Configuration.baseUrl = "https://qa-guru.github.io";
//        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000; // default 4000

        Configuration.browser = webConfig.getBrowser();
        Configuration.browserVersion = webConfig.getBrowserVersion();
        Configuration.browserSize = webConfig.getBrowserSize();
        Configuration.remote = webConfig.getRemoteUrl();
    }

    @AfterEach
    void tearDown () {
        closeWebDriver();
    }
}


