package tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.PageObject;
import testdata.CustomerCategories;

import java.util.stream.Stream;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

@Story("BankWebMain")
public class WebMainTest extends TestBase {
    PageObject pageObject = new PageObject();

   static Stream<Arguments> customerCategoryProvider() {
        return Stream.of(
                Arguments.of(CustomerCategories.PHYSICAL),
                Arguments.of(CustomerCategories.LEGAL),
                Arguments.of(CustomerCategories.FEA),
                Arguments.of(CustomerCategories.MARKETS),
                Arguments.of(CustomerCategories.INVESTORS)
        );
    }

    @DisplayName("Header Tests")
    @MethodSource("customerCategoryProvider")
    @ParameterizedTest(name = "Проверка хэдера для: {0}")
    @Tag("Blocker")
    void selectAndValidateClientCategory(CustomerCategories category) {
        step("Open Web Main", () -> {
            pageObject.openWebMain();
        });

        step("Select Category", () -> {
            pageObject.selectCategory(category);
        });

        step("Validate buttons", () -> {
            pageObject.checkSubmenuButtons();
        });
    }
}