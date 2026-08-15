package helpers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import config.WebConfig;
import io.qameta.allure.Attachment;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class Attach {

    private static final WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] pageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(String attachName, String message) {
        return message;
    }

    public static void browserConsoleLogs() {
        attachAsText(
                "Browser console logs",
                String.join("\n", Selenide.getWebDriverLogs(BROWSER))
        );
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String addVideo() {
        String sessionId = Selenide.sessionId() != null
                ? Selenide.sessionId().toString()
                : null;  // Преобразуем в String

        // Если без сессии (локальный запуск без Selenoid) — не добавляем битую ссылку
        if (sessionId == null) {
            return "<html><body><p>📹 Video not available (local run)</p></body></html>";
        }

        // Фиксированный URL для Selenoid — не конфигурируется
        String videoBaseUrl = "https://selenoid.autotests.cloud/video";

        return "<html><body>" +
                "<video width='100%' height='100%' controls autoplay>" +
                "<source src='" + videoBaseUrl + "/" + sessionId + ".mp4' type='video/mp4'>" +
                "</video></body></html>";
    }
   }