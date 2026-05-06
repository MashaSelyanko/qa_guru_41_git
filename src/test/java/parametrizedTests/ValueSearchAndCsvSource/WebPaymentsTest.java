package parametrizedTests.ValueSearchAndCsvSource;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class WebPaymentsTest {

    @ValueSource(strings = {
            "Аннуитетный", "Дифференцированный"
    })
    @ParameterizedTest(name = "Проверка отображения сумм кредита при смене типа платежа")
    @Tag("Smoke")
    void checkAmountChangeOnTypeSwitch(String paymentType) {
        $(byText(paymentType)).click();

        $(".ResultBlock_circle__vAq83 ResultBlock_orange__n96Zt")
                .shouldBe(visible);       //проверка, что видна сумма платежа
        $(".ResultBlock_circle__vAq83 ResultBlock_orange__n96Zt")
                .shouldHave(text("₽"));   //проверка, что сумма содержит символ рубля
    }

    ;

    @CsvSource(value = {
            "Аннуитетный; 470 601,77",
            "Диффиренцированный;  381 250,00"
    }, delimiter = ';')
    @ParameterizedTest (name = "Проверка зависимости суммы платежа от типа платежа")
    void shouldCheckExactAmountForEachPaymentType(String paymentType, String expectedSum) {
        //выбор типа платежа
        $(byText(paymentType)).click();

        $(".ResultBlock_circle__vAq83 ResultBlock_orange__n96Zt")
                .shouldHave(text(expectedSum));
    }

}

