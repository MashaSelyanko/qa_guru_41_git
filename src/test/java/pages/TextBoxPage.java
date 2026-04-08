package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static testdata.TestData.*;

public class TextBoxPage {
    //Elements
    private SelenideElement userFirstNameInput = $("#firstName");
    private SelenideElement userLastNameInput = $("#lastName");
    private SelenideElement userEmailInput = $("#userEmail");
    private SelenideElement submitButton = $("#submit");
    private SelenideElement outputResults = $(".table-responsive");
    private SelenideElement userBirthInput = $("#dateOfBirthInput");
    private SelenideElement userNumberInput = $("#userNumber");
    private SelenideElement userYearInput = $(".react-datepicker__year-select");
    private SelenideElement userMonthInput = $(".react-datepicker__month-select");
    private SelenideElement userDayInput = $("#datepickerDays");
    private SelenideElement userAddressTextarea = $("#currentAddress");
    private SelenideElement userSubjectInput = $("#subjectsInput");
    private SelenideElement uploadPicture = $("#uploadPicture");
    private SelenideElement userStateCombobox = $("#state");
    private SelenideElement userCityCombobox = $("#city");


    //Actions
    public void openPage() {
        open("/one-page-form/automation-practice-form.html");
    }

    public void typeUserFirstName(String value) {
        userFirstNameInput.setValue(value);
    }

    public void typeUserLastName(String value) {
        userLastNameInput.setValue(value);
    }

    public void typeUserEmail(String value) {
        userEmailInput.setValue(value);
    }

    public void submitForm() {
        submitButton.click();
    }

    public void setGender(String gender) {
        $(byText(gender)).click(); //т.к. локатор динамический, не объявляли его отдельно в переменных, а инкапсулировали в логику в метод сразу
    }

    public void setUserBirthInput() {
        userBirthInput.click();
    }

    public void setUserTelNumberInput(String number) {
        userNumberInput.setValue(number);
    }

    public void setUserYearInput(String number) {
        userYearInput.selectOption(number);
    }

    public void setUserMonthInput(String month) {
        int monthValue = Integer.parseInt(month) - 1;
        userMonthInput.selectOptionByValue(month);
    }

    public void setUserDayInput(String day) {
        userDayInput.click();
    }

    public void setSubjectInput(String role) {
        userSubjectInput.scrollTo().
                setValue(role).
                pressEnter();
    }

    public void setHobbies(String hobbies) {
        $(byText(hobbies)).click();
    }

    public void setUploadPicture(String value) {
        uploadPicture.uploadFromClasspath(value);
    }

    public void setUserAddressTextarea(String value) {
        userAddressTextarea.setValue(value);
    }

    public void setUserStateCombobox(String state) {
        userStateCombobox.click();
        $(byText(state)).click();
    }

    public void setUserCityCombobox(String city) {
        userCityCombobox.click();
        $(byText(city)).click();
    }












    //Tests
    public void checkField(String key, String value) {
        outputResults.$(byText(key)).parent().shouldHave(text(value));
    }

}








