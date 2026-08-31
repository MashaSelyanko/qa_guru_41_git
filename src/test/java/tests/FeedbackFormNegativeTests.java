package tests;

import io.qameta.allure.Story;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.PageObject;
import testdata.TestData;

@Story("FeedbackFormNegative")
public class FeedbackFormNegativeTests extends TestBase {

    PageObject pageObject = new PageObject();
    TestData testData = new TestData();

    //негативный тест - ввод на латинице ФИО в форме обратной связи
    @Test
    @DisplayName("Full name must contain only Cyrillic characters")
    @Tag("Blocker")
    void fillFeedbackFormNegative() {
        step("Open feedback form", () -> {
            open("/retail/feedback/fl");
        });

        step("Type full name: " + testData.fullName, () -> {
            pageObject.typefullNameInputNegative(testData.fullNameNegative);
        });

        step("Type birth date: " + testData.dateBirthday, () -> {
            pageObject.typeBirthInput(testData.dateBirthday);
        });

        step("Type phone number: " + testData.userNumber, () -> {
            pageObject.setUserTelNumberInput(testData.userNumber);
        });

        step("Type email: " + testData.userEmail, () -> {
            pageObject.typeUserEmail(testData.userEmail);
        });

        step("Click the 'Continue' button", () -> {
            pageObject.submitForm();
        });

        step("Click the 'Continue' button", () -> {
            pageObject.submitForm();
        });

        // Проверка валидации поля ФИО
        step("Verify transition to Step 2", () -> {
            pageObject.verifyRussianLettersErrorIsVisible();
        });
    }
}