import com.codeborne.selenide.WebElementCondition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static testdata.TestData.*;

public class CheckForm extends TestBase {

        @Test
        void fillAndCheckForm() {
            open("/one-page-form/automation-practice-form.html");

            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()"); //скрываем рекламу

            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(userEmail);
            $(byText(gender)).click();

            $("#dateOfBirthInput").click();
            $("#userNumber").setValue((userNumber));
            $("[class=react-datepicker__year-select]").selectOption(year);
            $(byText(month)).click();

            int monthValue = Integer.parseInt(month); //отнимаем 1 от номера месяца чтоб он совпал с результатом в таблице
            $("[class=react-datepicker__month-select]").selectOptionByValue(String.valueOf(monthValue)); //выбор по числу месяца


            $("#subjectsWrapper").click();
            $(byText(role)).click();

            $(byText(hobbies)).click();

            $("#uploadPicture").uploadFromClasspath(picture);
            $("#currentAddress").setValue(currentAddress);

            $("#state").click();
            $(byText(state)).click();

            $("#city").click();
            $(byText(city)).click();

            $("#submit").click();

            //проверка данных в итоговой таблице
            $$("tbody#resultBody tr").get(0).shouldHave(text(firstEndlastName));
            $$("tbody#resultBody tr").get(1).shouldHave(text(userEmail));
            $$("tbody#resultBody tr").get(2).shouldHave(text(gender));
            $$("tbody#resultBody tr").get(3).shouldHave(text(userNumber));

            $$("tbody#resultBody tr").get(4).shouldHave(text(String.format("%s-%02d-%02d",
                    Integer.parseInt(year),
                    Integer.parseInt(month) + 1,
                    Integer.parseInt(day)))); //так как в таблицу попадают значения без ведущего нуля 7 а не 07, в проверку добавлена обработка этих значений
                                                //спецификатор %02d делает длиной 2 символа, добавляя 0

                        $$("tbody#resultBody tr").get(5).shouldHave(text(role));
            $$("tbody#resultBody tr").get(6).shouldHave(text(hobbies));
            $$("tbody#resultBody tr").get(7).shouldHave(text(picture));
            $$("tbody#resultBody tr").get(8).shouldHave(text(currentAddress));
            $$("tbody#resultBody tr").get(9).shouldHave(text(state));
            $("#city").shouldHave(text(city));

            $("#closeModal").click();

        }

        private void shouldHave(WebElementCondition text) {
        }
    }
