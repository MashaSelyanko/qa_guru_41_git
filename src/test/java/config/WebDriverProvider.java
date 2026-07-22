//package qa.guru.owner.config;
//
//import config.WebConfig;
//import org.aeonbits.owner.ConfigFactory;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import java.util.function.Supplier;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.Capabilities;
//
//import static com.codeborne.selenide.Browsers.CHROME;
//import static com.codeborne.selenide.Browsers.FIREFOX;
//
//public class WebDriverProvider implements Supplier<WebDriver> {
//
//    private final WebConfig config;
//
//    public WebDriverProvider() {
//        // Если переменная env не передана, принудительно ставим "local"
//        if (System.getProperty("env") == null) {
//            System.setProperty("env", "local");
//        }
//        this.config = ConfigFactory.create(WebConfig.class, System.getProperties());
//    }
//    public WebDriverProvider(Capabilities capabilities) {
//        if (System.getProperty("env") == null) {
//            System.setProperty("env", "local");
//        }
//        this.config = ConfigFactory.create(WebConfig.class, System.getProperties());
//    }
//
//    @Override
//    public WebDriver get() {
//        WebDriver driver = createDriver();
//        driver.get(config.getBaseUrl());
//        return driver;
//    }
//
//    public WebDriver createDriver() {
//        switch (config.getBrowser()) {
//            case CHROME: {
//                ChromeOptions options = new ChromeOptions();
//                // Задаем версию браузера из конфига
//                options.setBrowserVersion(config.getBrowserVersion());
//                // Если в конфиге isRemote=true, отправляем в Selenoid
//                if (config.isRemote()) {
//                    return new RemoteWebDriver(config.getRemoteUrl(), options);
//                }
//                // Иначе запускаем локально
//                WebDriverManager.chromedriver().setup();
//                return new ChromeDriver();
//            }
//            case FIREFOX: {
//                FirefoxOptions options = new FirefoxOptions();
//                options.setBrowserVersion(config.getBrowserVersion());
//
//                if (config.isRemote()) {
//                    return new RemoteWebDriver(config.getRemoteUrl(), options);
//                }
//                WebDriverManager.firefoxdriver().setup();
//                return new FirefoxDriver();
//            }
//            default: {
//                throw new RuntimeException("No such driver");
//            }
//        }
//    }
//
//}