
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    static void setupSelenideConfig () {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://qa-guru.github.io";
        Configuration.timeout = 10000; // default 4000
    }

    @AfterEach
    void tearDowb () {
        closeWebDriver();
    }
}


