package tests;//import com.codeborne.selenide.WebElementCondition;

import org.junit.jupiter.api.*;
import pages.*;
import static com.codeborne.selenide.Selenide.*;
import static testdata.TestData.*;
import static utils.RandomUtils.*;


public class CheckForm extends TestBase {
    TextBoxPage textBoxPage = new TextBoxPage();

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
        textBoxPage.checkResult("Student Name", firstName + " " + lastName);
        textBoxPage.checkResult("Student Email", userEmail);
        textBoxPage.checkResult("Gender", gender);
        textBoxPage.checkResult("Mobile", userNumber);
        textBoxPage.checkDate("Date of Birth", day, month, year);
        textBoxPage.checkResult("Subjects", role);
        textBoxPage.checkResult("Hobbies", hobbies);
        textBoxPage.checkResult("Picture", picture);
        textBoxPage.checkResult("Address", currentAddress);
        textBoxPage.checkStateAndCity("State and City", state, city);
        textBoxPage.checkSubmit();

    }
}