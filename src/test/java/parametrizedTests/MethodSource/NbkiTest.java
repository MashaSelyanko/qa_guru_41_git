package parametrizedTests.MethodSource;

import com.codeborne.selenide.CollectionCondition;
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

        // убираем банне про куки
        if ($(withText("Принимаю")).exists()) {
            $(withText("Принимаю")).click();
        }

        $(".headertop__links").shouldBe(Condition.visible).click();

        // Проверка, активна ли уже нужная категория
        $$(".headertop__link.current a")
                .find(Condition.text(category.typeName));

        // Берем активную категорию
        SelenideElement activeCategory = $(".headertop__link.current a");
        boolean isAlreadyActive = activeCategory.exists();

        if (!isAlreadyActive) {

         // Выбираем, только если категория не активна
            $$(".headertop__dropdown a")
                    .filterBy(partialText(category.typeName))
                    .first()
                    .click();
        }
        // Проверка, что появилась хотя бы одна кнопка хэдера
        $$(".headermenu li a")
                .filterBy(partialText(category.expectedButtons.get(0)))
                .first()
                .shouldBe(Condition.visible);

        // Проверяем кнопки хэдера
        var menuLinks = $$(".headermenu li a");
        if (category == CustomerCategories.CORPORATE) {
            // Для корп. клиентов исключаем спец. кнопку из общей проверки
            menuLinks.filterBy(Condition.not(partialText("Подключиться к НБКИ")))
                    .shouldHave(textsInAnyOrder(category.expectedButtons));
        } else {
            menuLinks.shouldHave(textsInAnyOrder(category.expectedButtons));
        }

        // Проверка кнопки "Войти"
        var loginBtn = $$(".headerright span, .headerright a")
                .filterBy(partialText("Войти"))
                .first();

        if (category.hasLoginButton) {
            loginBtn.shouldBe(Condition.visible);
        } else {
            loginBtn.shouldNotBe(Condition.visible);
        }

        // 5. Проверка доп.кнопки
        if (category.extraButton != null) {
            $(byText(category.extraButton)).shouldBe(Condition.visible);
        }
    }
}