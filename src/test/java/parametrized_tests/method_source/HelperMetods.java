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

        if (acceptButton.isDisplayed()) {
            acceptButton.click();
        }
    }

    public void selectCategoryIfNotActive(CustomerCategories category) {
        String targetName = category.typeName;

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
                    .shouldBe(Condition.visible)
                    .click();
        }
    }

    public void checkHeaderButtons(CustomerCategories category) {
        var menuLinks = $$(".headermenu li a");
        if (category == CustomerCategories.CORPORATE) {
            // У корп.клиентов исключаем синюю кнопку из общего списка меню
            menuLinks.filterBy(Condition.not(Condition.partialText("Подключиться к НБКИ")))
                    .shouldHave(CollectionCondition.textsInAnyOrder(category.expectedButtons));
        } else {
            menuLinks.shouldHave(CollectionCondition.textsInAnyOrder(category.expectedButtons));
        }
    }
        // Проверка кнопки "Войти"
public void checkLoginButtonVisibility(CustomerCategories category) {
            var loginBtn = $$(".headerright span, .headerright a")
                    .filterBy(Condition.partialText("Войти"));
            if (category.hasLoginButton) {
                loginBtn.shouldHave(CollectionCondition.sizeGreaterThan(0));
                ;
            } else {
                loginBtn.shouldHave(CollectionCondition.size(0));
                ;
            }
        }
public void checkExtraButtonVisibility(CustomerCategories category) {
            // Проверка доп.кнопки
            if (category.extraButton != null) {
                $(Selectors.byText(category.extraButton)).shouldBe(Condition.visible);
            }
        }
    }
