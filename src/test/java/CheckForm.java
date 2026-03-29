
import com.codeborne.selenide.ClickOptions;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;


public class CheckForm {

@Test
void fillAndCheckForm() {
    open("https://qa-guru.github.io/one-page-form/automation-practice-form.html");

    executeJavaScript("$('#fixedban').remove()");
    executeJavaScript("$('footer').remove()"); //скрываем рекламу

    $("[id=firstName]").setValue("Ivan");
    $("[id=lastName]").setValue("Petrov");
    $("[id=userEmail]").setValue("Petrov@mail.com");
    $("[for=gender-radio-1]").click();
    $("[id=userNumber]").setValue("7123456789");
    $("[id=dateOfBirthInput]").click();

    $("[class=react-datepicker__year-select]").selectOption("2008");
    $("[class=react-datepicker__month-select]").selectOption("July");
    $(".react-datepicker__day.react-datepicker__day--004").click();

    $("[for=subjectsInput]").click();
    $(byText("Maths")).click();

    $("[id=uploadPicture]").uploadFromClasspath("img.png");
    $("[id=currentAddress]").setValue("Moskau");

    $("[id=state]").click();
    $(byText("NCR")).click();

    $("[id=city]").click();
    $(byText("Delhi")).click();

    $("[id=submit]").click();

//проверка данных в итоговой таблице
    $(".table-responsive").shouldHave(
            text("Ivan Petrov"),
            text("Petrov@mail.com"),
            text("Male"),
            text("7123456789"),
            text("2008-07-04"),
            text("Maths"),
            text("Moskau")
    );
    $("[id=closeModal]").click();

}
}

