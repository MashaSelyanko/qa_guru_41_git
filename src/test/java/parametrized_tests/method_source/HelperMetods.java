package parametrized_tests.method_source;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;

import static com.codeborne.selenide.Condition.partialText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HelperMetods {

    public void acceptCookiesIfVisible() {
        var acceptButton = $(withText("Принимаю"));

        if (acceptButton.exists()) {
            acceptButton.click();
        }
    }

    public void selectCategoryIfNotActive(CustomerCategories category) {
        String targetName = category.typeName;

        // клик на список категорий
        $(".headertop__links").shouldBe(Condition.visible).click();

        // активна ли нужная категория
        String activeCategoryText = $$(".headertop__link.current a")
                .first()
                .getText();
        boolean isAlreadyActive = activeCategoryText.equals(category.typeName);

        // меняем категорию, только если она ещё не активна
        if (!isAlreadyActive) {
            $$(".headertop__dropdown a")
                    .filterBy(partialText(category.typeName))
                    .first()
                    .shouldBe(Condition.visible)  // Ждём, пока элемент станет видимым
                    .click();
        }
    }

    public void checkHeaderButtons(CustomerCategories category) {
        // 1. Проверка основных кнопок меню
        var menuLinks = $$(".headermenu li a");
        if (category == CustomerCategories.CORPORATE) {
            // У корпоративных клиентов исключаем синюю кнопку из общего списка меню
            menuLinks.filterBy(Condition.not(Condition.partialText("Подключиться к НБКИ")))
                    .shouldHave(CollectionCondition.textsInAnyOrder(category.expectedButtons));
        } else {
            menuLinks.shouldHave(CollectionCondition.textsInAnyOrder(category.expectedButtons));
        }

        // 2. Проверка кнопки "Войти"
        var loginBtn = $$(".headerright span, .headerright a")
                .filterBy(Condition.partialText("Войти"))
                .first();

        if (category.hasLoginButton) {
            loginBtn.shouldBe(Condition.visible);
        } else {
            loginBtn.shouldNotBe(Condition.visible);
        }

        // 3. Проверка дополнительной кнопки (если она задана)
        if (category.extraButton != null) {
            $(Selectors.byText(category.extraButton)).shouldBe(Condition.visible);
        }
    }
}