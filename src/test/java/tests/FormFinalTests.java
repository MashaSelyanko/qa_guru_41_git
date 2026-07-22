package tests;

import org.junit.jupiter.api.*;
import pages.*;
import pages.components.*;
import testdata.*;

public class FormFinalTests extends TestBase {
    TestData testData = new TestData();

    TextBoxPage textBoxPage = new TextBoxPage();
    TextBoxPageResult textBoxPageResult = new TextBoxPageResult();

    @Test
    void fillAndCheckForm() {
        textBoxPage.openPage();
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

    }
}
