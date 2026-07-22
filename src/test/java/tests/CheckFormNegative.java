package tests;

import org.junit.jupiter.api.*;
import pages.*;
import pages.components.*;
import testdata.*;
import static com.codeborne.selenide.Selenide.*;
import static utils.RandomUtils.*;

public class CheckFormNegative extends TestBase {
    TestData testData = new TestData();

    TextBoxPage textBoxPage = new TextBoxPage();
    TextBoxPageResult textBoxPageResult = new TextBoxPageResult();


    @BeforeEach
        //метод/аннотация - инструмент для выполнения перед началом каждого теста
    void prepareRandomData() {
        testData.city = selectCity(testData.state);
    }

    @Test
    void checkFormMinimumTests() {
        textBoxPage.openPage();

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()"); //скрываем рекламу

        textBoxPage.typeUserFirstName(testData.firstNameNegative);
        textBoxPage.typeUserLastName(testData.lastNameNegative);
        textBoxPage.setGender(testData.gender);
        textBoxPage.setUserTelNumberInput(testData.userNumberNegative);
        textBoxPage.submitForm();
        //проверка данных в итоговой таблице
        textBoxPageResult.checkResult("Student Name", testData.firstNameNegative + " " + testData.lastNameNegative);
        textBoxPageResult.checkResult("Gender", testData.gender);
        textBoxPageResult.checkResult("Mobile", testData.userNumberNegative);
        textBoxPageResult.checkSubmit();
    }

    @Test
    void checkFormNegativeTests() {
        textBoxPage.openPage();

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()"); //скрываем рекламу

        textBoxPage.typeUserFirstName(testData.firstNameNegative);
        textBoxPage.submitForm();

        //проверка отображения ошибки заполнения
        textBoxPage.checkShouldHave("Please fill required fields and enter a valid 10-digit mobile number.");
        textBoxPage.checkFirstNameIsVisible();
    }
}
