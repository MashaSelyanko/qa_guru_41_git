import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.WebElementCondition;
import org.junit.jupiter.api.Test;

import static TestDate.TestDate.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;


public class CheckForm extends TestBase {

@Test
void fillAndCheckForm() {
    open("/one-page-form/automation-practice-form.html");

    executeJavaScript("$('#fixedban').remove()");
    executeJavaScript("$('footer').remove()"); //скрываем рекламу

    $("#firstName").setValue(firstName);
    $("#lastName").setValue(lastName);
    $("#userEmail").setValue(userEmail);
    $("[for=gender-radio-1]").click();
    $("#userNumber").setValue(userNumber);
    $("#dateOfBirthInput").click();

    $("[class=react-datepicker__year-select]").selectOption(year);
    $("[class=react-datepicker__month-select]").selectOption(month);
    $(".react-datepicker__day.react-datepicker__day--004").click();

    $("#subjectsWrapper").click();
    $(byText(role)).click();

    $("label[for=hobbies-checkbox-1]").click();

    $("#uploadPicture").uploadFromClasspath("img.png");
    $("#currentAddress").setValue(currentAddress);

    $("#state").click();
    $(byText(state)).click();

    $("#city").click();
    $(byText(city)).click();

    $("#submit").click();

//проверка данных в итоговой таблице
   $$("tbody#resultBody tr").get(0).shouldHave(text(firstEndlastName));
    $$("tbody#resultBody tr").get(1).shouldHave(text(userEmail));
    $$("tbody#resultBody tr").get(2).shouldHave(text("Male"));
    $$("tbody#resultBody tr").get(3).shouldHave(text(userNumber));
    $$("tbody#resultBody tr").get(4).shouldHave(text("2008-07-04"));
    $$("tbody#resultBody tr").get(5).shouldHave(text(role));
    $$("tbody#resultBody tr").get(6).shouldHave(text("Sports"));
    $$("tbody#resultBody tr").get(7).shouldHave(text("img.png"));
    $$("tbody#resultBody tr").get(8).shouldHave(text(currentAddress));
    $$("tbody#resultBody tr").get(9).shouldHave(text(state));
    $("#city").shouldHave(text(city));

    $("#closeModal").click();

}

    private void shouldHave(WebElementCondition text) {
    }
}

