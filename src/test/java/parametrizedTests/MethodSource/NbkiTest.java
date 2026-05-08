package parametrizedTests.MethodSource;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.textsInAnyOrder;
import static com.codeborne.selenide.Condition.partialText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class NbkiTest {

    static Stream<Arguments> customerCategoryProvider() {
        return Stream.of(
                Arguments.of(CustomerCategories.PHYSICAL),
                Arguments.of(CustomerCategories.LEGAL),
                Arguments.of(CustomerCategories.CORPORATE)
        );
    }

    @BeforeAll
    static void globalSetup() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
    }

    @MethodSource("customerCategoryProvider")
    @ParameterizedTest(name = "Проверка хэдера для: {0}")
    @Tag("Blocker")
    void successfulCustomerCategoriesTest(CustomerCategories category) {
        open("https://nbki.ru/");

        // убираем баннер-куки
        if ($(withText("Принимаю")).exists()) {
            $(withText("Принимаю")).click();
        }

        // клик на список категорий
        $(".headertop__links").shouldBe(Condition.visible).click();

        // активна ли нужная категория
        String activeCategoryText = $$(".headertop__link.current a")
                .first()
                .getText();
        boolean isAlreadyActive = activeCategoryText.equals(category.typeName);

        // меняем категорию, только если она ещё не активна
        if (!isAlreadyActive) {
            $$(".headertop__dropdown a")
                    .filterBy(partialText(category.typeName))
                    .first()
                    .shouldBe(Condition.visible)  // Ждём, пока элемент станет видимым
                    .click();
        }

        // проверка, что появилась хотя бы одна кнопка хэдера
        $$(".headermenu li a")
                .filterBy(partialText(category.expectedButtons.get(0)))
                .first()
                .shouldBe(Condition.visible);

        // проверки кнопок хэдера
        var menuLinks = $$(".headermenu li a");
        if (category == CustomerCategories.CORPORATE) {
            // у корп.клиентов исключаем спец.кнопку из общей проверки
            menuLinks.filterBy(Condition.not(partialText("Подключиться к НБКИ")))
                    .shouldHave(textsInAnyOrder(category.expectedButtons));
        } else {
            menuLinks.shouldHave(textsInAnyOrder(category.expectedButtons));
        }

        // проверка кнопки "Войти"
        var loginBtn = $$(".headerright span, .headerright a")
                .filterBy(partialText("Войти"))
                .first();

        if (category.hasLoginButton) {
            loginBtn.shouldBe(Condition.visible);
        } else {
            loginBtn.shouldNotBe(Condition.visible);
        }

        // проверка доп. кнопки
        if (category.extraButton != null) {
            $(byText(category.extraButton)).shouldBe(Condition.visible);
        }
    }
}