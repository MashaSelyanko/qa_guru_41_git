package allure_Reports;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class LambdaTest {
    private static final String REPOSITORY = "MashaSelyanko/qa_guru_41_git";
    private static final int ISSUE = 3;

    @Test
    @Feature("Issue в репозитории")
    @Story("Создание Issue")
    @Owner("MashaSelyanko")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Создание Issue для авторизованного пользователя")

    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });

        step("Ищем репозиторий " + LambdaTest.REPOSITORY, () -> {
            $("button.header-search-button").click();
            $("#query-builder-test").setValue("MashaSelyanko/qa_guru_41_git");
            $("#query-builder-test").submit();
        });

        step("Кликаем по ссылке репозитория " + LambdaTest.REPOSITORY, () -> {
            $(linkText(LambdaTest.REPOSITORY)).click();
        });

        step("Нажимаем на Issues", () -> {
        $("#issues-repo-tab-count").click();
    });

        step("Проверяем наличие Issue с номером " + LambdaTest.ISSUE, () -> {
            $(withText("#" + LambdaTest.ISSUE)).should(Condition.exist);
        });

    }
}

