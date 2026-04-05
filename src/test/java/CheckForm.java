import com.codeborne.selenide.ClickOptions;
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

    $("#uploadPicture").uploadFromClasspath("img.png");
    $("#currentAddress").setValue(currentAddress);

    $("#state").click();
    $(byText(state)).click();

    $("#city").click();
    $(byText(city)).click();

    $("#submit").click();

//проверка данных в итоговой таблице
    $(".table-responsive").shouldHave(text(firstEndlastName));
    $(".table-responsive").shouldHave(text(userEmail));
    $(".table-responsive").shouldHave(text("Male"));
    $(".table-responsive").shouldHave(text(userNumber));
    $(".table-responsive").shouldHave(text("2008-07-04"));
    $(".table-responsive").shouldHave(text(role));
    $(".table-responsive").shouldHave(text(currentAddress));
    $("#closeModal").click();

}
}

