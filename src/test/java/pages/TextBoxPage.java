package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
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

    private SelenideElement textErrorAutomation = $("#formError"); //текст негативного теста

    //Actions
    @Step("Open registration page /automation-practice-form")
    public void openPage() {
        open("/automation-practice-form");
    }

    @Step("Type first name \"{value}\"")
    public void typeUserFirstName(String value) {
        userFirstNameInput.setValue(value);
    }

    @Step("Type last name \"{value}\"")
    public void typeUserLastName(String value) {
        userLastNameInput.setValue(value);
    }

    @Step("Type email \"{value}\"")
    public void typeUserEmail(String value) {
        userEmailInput.shouldBe(Condition.editable).setValue(value); //+ждем возможность редактирования
    }

    @Step("Type gender \"{gender}\"")
    public void setGender(String gender) {
        $(byText(gender)).click(); //т.к. локатор динамический, не объявляли его отдельно в переменных, а инкапсулировали в логику в метод сразу
    }

    @Step("Type telefon number {number}")
    public void setUserTelNumberInput(String number) {
        userNumberInput.setValue(number);
    }

    @Step("Type date of birth {day} {month} {year}")
    public TextBoxPage setDateOfBirth(String day, String month, String year) {
        userBirthInput.click();
        calendar.setDate(day, month, year);

        return this;
    }

    public void setSubjectInput(String role) {
        userSubjectInput.click();
        userSubjectInput.sendKeys(role);
        userSubjectInput.pressEnter();
    }

    @Step("Type hobbies \"{hobbies}\"")
    public void setHobbies(String hobbies) {
        $(byText(hobbies)).click();
    }

    @Step("Type picture \"{value}\"")
    public void setUploadPicture(String value) {
        uploadPicture.uploadFromClasspath(value);
    }

    @Step("Type address \"{value}\"")
    public void setUserAddressTextarea(String value) {
        userAddressTextarea.setValue(value);
    }

    @Step("Type state and city {state} {city}")
    public void setStateAndCity(String state, String city) {
        stateAndCity.setStateAndCity(state, city);
    }

    @Step("Submit form")
    public void submitForm() {
        submitButton.click();
    }

    //Tests

    @Step("Check date")
    public void checkDate(String key, String day, String month, String year) {
        String monthName = month;
        // Если вместо названия пришло число (например, "6"), переводим его в текст
        if (month.matches("\\d+")) {
            int monthIndex = Integer.parseInt(month) - 1; // "6" станет индексом 5
            String[] months = {
                    "January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"
            };

            if (monthIndex >= 0 && monthIndex < 12) {
                monthName = months[monthIndex]; // Извлечет "June"
            }
        }
        // Собираем финальную строку для проверки
        String expectedDate = day + " " + monthName + "," + year;
        outputResults.$(com.codeborne.selenide.Selectors.byText(key))
                .parent()
                .$("td", 1)
                .shouldHave(com.codeborne.selenide.Condition.text(expectedDate));
    }

    @Step("Check should have")
    public void checkShouldHave(String value) {
        textErrorAutomation.shouldHave(text(value)); //текс ошибки при негативной проверке
    }

    @Step("check first name is visible")
    public void checkFirstNameIsVisible() {
        userFirstNameInput.shouldBe(visible);
    }
}









