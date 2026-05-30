package tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import testdata.TestData;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

@Story("FeedbackForm")
public class FeedbackFormTests extends TestBase {
    TextBoxPage textBoxPage = new TextBoxPage();
    TestData testData = new TestData();

    //проверка перехода на шаг 2 после заполнения формы обратной связи
    @Test
    @DisplayName("Navigation to Step 2 after valid feedback form completion")
    @Tag("Blocker")
    void fillFeedbackForm() {
        step("Open feedback form", () -> {
            textBoxPage.openFeedbackForm();
        });

        step("Type full name: " + testData.fullName, () -> {
            textBoxPage.typefullNameInput(testData.fullName);
        });

        step("Type birth date: " + testData.dateBirthday, () -> {
            textBoxPage.typeBirthInput(testData.dateBirthday);
        });

        step("Type phone number: " + testData.userNumber, () -> {
            textBoxPage.setUserTelNumberInput(testData.userNumber);
        });

        step("Type email: " + testData.userEmail, () -> {
            textBoxPage.typeUserEmail(testData.userEmail);
        });

        step("Click the 'Continue' button", () -> {
            textBoxPage.submitForm();
        });

        step("Click the 'Continue' button", () -> {
            textBoxPage.submitForm();
        });

        // Проверка перехода на "Шаг 2 из 4":
        step("Verify transition to Step 2", () -> {
            textBoxPage.verifyStepTwoIsOpened();
        });
    }
}

