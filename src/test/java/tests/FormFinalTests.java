package tests;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

import static testdata.TestData.*;

public class FormFinalTests {
    TextBoxPage textBoxPage = new TextBoxPage();

        @Test
        void fillAndCheckForm() {

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
