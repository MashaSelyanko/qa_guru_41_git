package tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.PageObject;
import testdata.TestData;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

@Story("FeedbackForm")
public class FeedbackFormTests extends TestBase {
    PageObject pageObject = new PageObject();
    TestData testData = new TestData();

    //проверка перехода на шаг 2 после заполнения формы обратной связи
    @Test
    @DisplayName("Navigation to Step 2 after valid feedback form completion")
    @Tag("Blocker")
    void fillFeedbackForm() {
        step("Open feedback form", () -> {
            open("/retail/feedback/fl");
        });

        step("Type full name: " + testData.fullName, () -> {
            pageObject.typefullNameInput(testData.fullName);
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

        // Проверка перехода на "Шаг 2 из 4":
        step("Verify transition to Step 2", () -> {
            pageObject.verifyStepTwoIsOpened();
        });
    }
}

