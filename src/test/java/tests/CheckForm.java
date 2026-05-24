package tests;//import com.codeborne.selenide.WebElementCondition;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import pages.components.TextBoxPageResult;
import testdata.TestData;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static io.qameta.allure.Allure.step;
import static utils.RandomUtils.selectCity;

@Story("Registration form")
public class CheckForm extends TestBase {
    TestData testData = new TestData();
    TextBoxPage textBoxPage = new TextBoxPage();
    TextBoxPageResult textBoxPageResult = new TextBoxPageResult();

    @BeforeEach
    void prepareRandomData() {
        testData.city = selectCity(testData.state);
    }

    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }               //добавляет скриншоты

    @Test
    @DisplayName("Fill and check form tests")
    void fillAndCheckFormTests_with_faker() {
        step("Open registration form", () -> {
            textBoxPage.openPage();
            executeJavaScript("document.querySelector('#fixedban')?.remove()");
            executeJavaScript("document.querySelector('footer')?.remove()");
        });

        step("Fill registration form", () -> {
            textBoxPage.typeUserFirstName(testData.firstName);
            textBoxPage.typeUserLastName(testData.lastName);
            textBoxPage.typeUserEmail(testData.userEmail);
            textBoxPage.setGender(testData.gender);
            textBoxPage.setDateOfBirth(testData.day, testData.month, testData.year);
            textBoxPage.setUserTelNumberInput(testData.userNumber);
            textBoxPage.setSubjectInput(testData.role);
            textBoxPage.setHobbies(testData.hobbies);
            textBoxPage.setUploadPicture(testData.picture);
            textBoxPage.setUserAddressTextarea(testData.currentAddress);
            textBoxPage.setStateAndCity(testData.state, testData.city);
            textBoxPage.submitForm();
        });

        step("Check registration form results", () -> {
            //Tests
            textBoxPageResult.checkResult("Student Name", testData.firstName + " " + testData.lastName);
            textBoxPageResult.checkResult("Student Email", testData.userEmail);
            textBoxPageResult.checkResult("Gender", testData.gender);
            textBoxPageResult.checkResult("Mobile", testData.userNumber);
            textBoxPage.checkDate("Date of Birth", testData.day, testData.month, testData.year);
            textBoxPageResult.checkResult("Subjects", testData.role);
            textBoxPageResult.checkResult("Hobbies", testData.hobbies);
            textBoxPageResult.checkResult("Picture", testData.picture);
            textBoxPageResult.checkResult("Address", testData.currentAddress);
            textBoxPageResult.checkStateAndCity("State and City", testData.state, testData.city);
            textBoxPageResult.checkSubmit();
        });

    }
}
