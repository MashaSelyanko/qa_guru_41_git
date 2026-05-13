package parametrized_tests.method_source;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;

public class NbkiTest {

    static Stream<Arguments> customerCategoryProvider() {
        return Stream.of(
                Arguments.of(CustomerCategories.PHYSICAL),
                Arguments.of(CustomerCategories.LEGAL),
                Arguments.of(CustomerCategories.CORPORATE)
        );
    }

    HelperMetods helperMetods = new HelperMetods();

    @BeforeAll
    static void globalSetup() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
    }

    @MethodSource("customerCategoryProvider")
    @ParameterizedTest(name = "Проверка хэдера для: {0}")
    @Tag("Blocker")
    void successfulCustomerCategoriesTest(CustomerCategories category) {

        open("https://nbki.ru");
        helperMetods.acceptCookiesIfVisible();
        helperMetods.selectCategoryIfNotActive(category);
        helperMetods.checkHeaderButtons(category);
    }
}