package tests;//import com.codeborne.selenide.WebElementCondition;

import org.junit.jupiter.api.*;
import pages.*;
import pages.components.*;

import static com.codeborne.selenide.Selenide.*;
import static testdata.TestData.*;
import static utils.RandomUtils.*;


public class CheckForm extends TestBase {
    TextBoxPage textBoxPage = new TextBoxPage();
    TextBoxPageResult textBoxPageResult = new TextBoxPageResult();

    @BeforeEach         //метод/аннотация - инструмент для выполнения перед началом каждого теста
    void prepareRandomData() {
        userEmail = getRandomEmail();
        state = getRandomState();
        city = getRandomCity(state);

    }

    @Test
    void fillAndCheckFormTests_with_faker() {
        textBoxPage.openPage();

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()"); //скрываем рекламу

        textBoxPage.typeUserFirstName(firstName);
        textBoxPage.typeUserLastName(lastName);
        textBoxPage.typeUserEmail(userEmail);
        textBoxPage.setGender(gender);
        textBoxPage.setDateOfBirth(day, month, year);
        textBoxPage.setUserTelNumberInput(userNumber);
        textBoxPage.setSubjectInput(role);
        textBoxPage.setHobbies(hobbies);
        textBoxPage.setUploadPicture(picture);
        textBoxPage.setUserAddressTextarea(currentAddress);
        textBoxPage.setStateAndCity(state, city);
        textBoxPage.submitForm();

        //Tests
        textBoxPageResult.checkResult("Student Name", firstName + " " + lastName);
        textBoxPageResult.checkResult("Student Email", userEmail);
        textBoxPageResult.checkResult("Gender", gender);
        textBoxPageResult.checkResult("Mobile", userNumber);
        textBoxPage.checkDate("Date of Birth", day, month, year);
        textBoxPageResult.checkResult("Subjects", role);
        textBoxPageResult.checkResult("Hobbies", hobbies);
        textBoxPageResult.checkResult("Picture", picture);
        textBoxPageResult.checkResult("Address", currentAddress);
        textBoxPageResult.checkStateAndCity("State and City", state, city);
        textBoxPageResult.checkSubmit();

    }
}