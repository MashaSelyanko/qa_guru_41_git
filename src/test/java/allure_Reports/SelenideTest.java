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
import static org.openqa.selenium.By.linkText;

public class SelenideTest {

    @Test
    @Feature("Issue в репозитории")
    @Story("Создание Issue")
    @Owner("MashaSelyanko")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Создание Issue для авторизованного пользователя")

    public void testIssueSearch() {
       SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $("button.header-search-button").click();
        $("#query-builder-test").setValue("MashaSelyanko/qa_guru_41_git");
        $("#query-builder-test").submit();

        $(linkText("MashaSelyanko/qa_guru_41_git")).click();
        $("#issues-repo-tab-count").click();
        $(withText("#3")).should(Condition.exist);
    }

}
