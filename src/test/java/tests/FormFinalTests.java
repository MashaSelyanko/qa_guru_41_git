package tests;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import pages.components.*;

import static testdata.TestData.*;

public class FormFinalTests {
    TextBoxPage textBoxPage = new TextBoxPage();
    TextBoxPageResult textBoxPageResult = new TextBoxPageResult();

        @Test
        void fillAndCheckForm() {

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
