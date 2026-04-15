package tests;

import org.junit.jupiter.api.*;
import pages.*;

import static com.codeborne.selenide.Selenide.*;
import static testdata.TestData.*;
import static utils.RandomUtils.*;
import static utils.RandomUtils.getRandomCity;
import static utils.RandomUtils.getRandomState;

public class CheckFormNegative extends TestBase {
    TextBoxPage textBoxPage = new TextBoxPage();

    @BeforeEach
        //метод/аннотация - инструмент для выполнения перед началом каждого теста
    void prepareRandomData() {
        userEmail = getRandomEmail();
        state = getRandomState();
        city = getRandomCity(state);}

        @Test
        void checkFormMinimumTests() {
            textBoxPage.openPage();

            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()"); //скрываем рекламу

            textBoxPage.typeUserFirstName(firstNameNegative);
            textBoxPage.typeUserLastName(lastNameNegative);
            textBoxPage.setGender(gender);
            textBoxPage.setUserTelNumberInput(userNumberNegative);
            textBoxPage.submitForm();
            //проверка данных в итоговой таблице
            textBoxPage.checkResult("Student Name", firstNameNegative + " " + lastNameNegative);
            textBoxPage.checkResult("Gender", gender);
            textBoxPage.checkResult("Mobile", userNumberNegative);
            textBoxPage.checkSubmit();
        }

        @Test
        void checkFormNegativeTests() {
            textBoxPage.openPage();

            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()"); //скрываем рекламу

            textBoxPage.typeUserFirstName(firstNameNegative);
            textBoxPage.submitForm();

            //проверка отображения ошибки заполнения
            textBoxPage.checkShouldHave("Please fill required fields and enter a valid 10-digit mobile number.");
            textBoxPage.checkFirstNameIsVisible();
        }
    }
