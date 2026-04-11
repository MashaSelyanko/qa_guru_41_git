package tests;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import static com.codeborne.selenide.Selenide.*;
import static testdata.TestData.*;

public class CheckFormNegative extends TestBase {
    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void testCheckFormMinimum() {
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
    void testCheckFormNegative() {
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