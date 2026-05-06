package parametrizedTests.MethodSource;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.stream.Stream;
import static com.codeborne.selenide.Selenide.*;

public class NbkiTest {

    static Stream<Arguments>customerCategoryProvider() {
        return Stream.of(
                Arguments.of(CustomerCategories.PHYSICAL),
                Arguments.of(CustomerCategories.LEGAL),
                Arguments.of(CustomerCategories.CORPORATE)
        );
    }

    @MethodSource("customerCategoryProvider")
    @ParameterizedTest(name = "Проверка кнопок хэдера при переключении категории клиентов{0}")
    @Tag("Blocker")
    void successfulCustomerCategoriesTest(CustomerCategories category) {
        Configuration.browserSize = "1920x1080";

        open("https://nbki.ru/");

        $(".headertop__links").shouldBe(Condition.visible).click();

        $$(".headertop__link")
                .find(Condition.partialText(category.typeName))
                .click();

        actions().sendKeys(Keys.ESCAPE).perform();
        $(".logo").click();

        $$(".headermenu li").shouldHave(
                CollectionCondition.texts(category.expectedButtons));

        $(".headerright")
                .shouldBe(category.hasLoginButton ? Condition.visible : Condition.hidden, Duration.ofSeconds(10));
                if(category.extraButton != null) {
                    $(".btn_main").shouldHave(Condition.text(category.extraButton));
        }
    }


}
