package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.StateAndCityComponent;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxPage {
    //Elements
    CalendarComponent calendar = new CalendarComponent();
    StateAndCityComponent stateAndCity = new StateAndCityComponent();

    private SelenideElement userFirstNameInput = $("#firstName");
    private SelenideElement userLastNameInput = $("#lastName");
    private SelenideElement userEmailInput = $("#userEmail");
    private SelenideElement outputResults = $(".table-responsive");
    private SelenideElement userBirthInput = $("#dateOfBirthInput");
    private SelenideElement userNumberInput = $("#userNumber");
    private SelenideElement userAddressTextarea = $("#currentAddress");
    private SelenideElement userSubjectInput = $("#subjectsInput");
    private SelenideElement uploadPicture = $("#uploadPicture");
    private SelenideElement submitButton = $("#submit");
    private SelenideElement closeModal = $("#closeModal"); //для теста
    private SelenideElement textErrorAutomation = $("#formError"); //текст негативного теста

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

    public void setGender(String gender) {
        $(byText(gender)).click(); //т.к. локатор динамический, не объявляли его отдельно в переменных, а инкапсулировали в логику в метод сразу
    }

    public void setUserTelNumberInput(String number) {
        userNumberInput.setValue(number);
    }

    public TextBoxPage setDateOfBirth(String day, String month, String year) {
        userBirthInput.click();
        calendar.setDate(day, month, year);

        return this;
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

    public void setStateAndCity(String state, String city) {
        stateAndCity.setStateAndCity(state, city);
    }

    public void submitForm() {
        submitButton.click();
    }

    //Tests
    public void checkResult(String key, String value) {
        outputResults.$(byText(key))
                .parent()                   //поднялись к родительской "tr"
                .$$("td")                //видим все "td"
                .get(1)                     //берем значение у второго (индекс 1)
                .shouldHave(text(value));   //проверяем
    }

    public void checkDate(String key, String day, String month, String year) {
        String expectedDate = String.format("%s-%02d-%02d",
                year,
                Integer.parseInt(month),
                Integer.parseInt(day));
        outputResults.$(byText(key))
                .parent()
                .$$("td")
                .get(1)
                .shouldHave(text(expectedDate));
    }

    public void checkStateAndCity(String key, String state, String city) {
        outputResults.$(byText(key))
                .parent()
                .$$("td")
                .get(1)
                .shouldHave(text(state + " " + city));
    }

    public void checkSubmit() {
        closeModal.click();
    }

    public void checkShouldHave(String value) {
        textErrorAutomation.shouldHave(text(value)); //текс ошибки при негативной проверке
    }

    public void checkFirstNameIsVisible() {
        userFirstNameInput.shouldBe(visible);
    }
}









