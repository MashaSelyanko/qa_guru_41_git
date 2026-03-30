import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


class checkFormMinimum extends TestBase {

    @Test
    void CheckFormMinimum() {
        open("https://qa-guru.github.io/one-page-form/automation-practice-form.html");

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()"); //скрываем рекламу

        $("#firstName").setValue("--");
        $("#lastName").setValue("--");
        $("[for=gender-radio-1]").click();
        $("#userNumber").setValue("1111111111");


        $("#submit").click();

//проверка данных в итоговой таблице
        $(".table-responsive").shouldHave(
                text("-- --"),
                text("Male"),
                text("1111111111")
                );
        $("[id=closeModal]").click();

    }


    @Test
void CheckFormNegative() {
    open("https://qa-guru.github.io/one-page-form/automation-practice-form.html");

    executeJavaScript("$('#fixedban').remove()");
    executeJavaScript("$('footer').remove()"); //скрываем рекламу

    $("#firstName").setValue("0");
    $("#submit").click();

//проверка отображения ошибки заполнения
   $("#formError").shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
   $("#firstName").shouldBe(visible);
}

}
