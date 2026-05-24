package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import pages.components.TextBoxPageResult;
import testdata.TestData;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static io.qameta.allure.Allure.step;
import static utils.RandomUtils.selectCity;

public class CheckFormNegative extends TestBase {
    TestData testData = new TestData();
    TextBoxPage textBoxPage = new TextBoxPage();
    TextBoxPageResult textBoxPageResult = new TextBoxPageResult();

        @BeforeEach
    void prepareRandomData() {
        testData.city = selectCity(testData.state);
           }

    @Test
    @DisplayName("Minimum check form tests")
    void checkFormMinimumTests() {
        step("Open registration form", () -> {
            textBoxPage.openPage();
        });

       step("Fill registration form", () -> {
            textBoxPage.typeUserFirstName(testData.firstNameNegative);
            textBoxPage.typeUserLastName(testData.lastNameNegative);
            textBoxPage.setGender(testData.gender);
            textBoxPage.setUserTelNumberInput(testData.userNumberNegative);
            textBoxPage.submitForm();
        });

        step("Check registration form results", () -> {
            //проверка данных в итоговой таблице
            textBoxPageResult.checkResult("Student Name", testData.firstNameNegative + " "
                    + testData.lastNameNegative);
            textBoxPageResult.checkResult("Gender", testData.gender);
            textBoxPageResult.checkResult("Mobile", testData.userNumberNegative);
            textBoxPageResult.checkSubmit();
        });
    }

    @Test
    @DisplayName("Negative check form tests")
    void checkFormNegativeTests() {
        step("Open registration form", () -> {
            textBoxPage.openPage();

            executeJavaScript("document.querySelector('#fixedban')?.remove()");
            executeJavaScript("document.querySelector('footer')?.remove()"); //скрываем рекламу
        });

        step("Fill negative registration form", () -> {
            textBoxPage.typeUserFirstName(testData.firstNameNegative);
            textBoxPage.submitForm();
        });

        step("Check negative registration form results", () -> {
            //проверка отображения ошибки заполнения
            //  textBoxPage.checkShouldHave("Please fill required fields and enter a valid 10-digit mobile number.");
            textBoxPage.checkFirstNameIsVisible();
        });
    }
}
