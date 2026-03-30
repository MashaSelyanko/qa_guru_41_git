import com.codeborne.selenide.ClickOptions;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;


public class CheckFormNegative extends TestBase {

    @Test
    void CheckForm() {
        open("https://qa-guru.github.io/one-page-form/automation-practice-form.html");

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()"); //скрываем рекламу

        $("[id=firstName]").setValue("--");
        $("[id=lastName]").setValue("--");
        $("[for=gender-radio-1]").click();
        $("[id=userNumber]").setValue("1111111111");


        $("[id=submit]").click();

//проверка данных в итоговой таблице
        $(".table-responsive").shouldHave(
                text("-- --"),
                text("Male"),
                text("1111111111")
                );
        $("[id=closeModal]").click();

    }
}


