package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import tests.CustomerCategories;
import static com.codeborne.selenide.Selenide.$$;

public class TextBoxPage {

    private final ElementsCollection categoryLinks = $$("div.css-1ndprbh a.chakra-link");
    private final ElementsCollection submenuButtons = $$("#header-menu-submenu_height nav button");

    //выбираем категорию
    public void selectCategory(CustomerCategories category) {
        categoryLinks.filterBy(Condition.partialText(category.typeName))
                .first()
                .click();
        // return this; УТОЧНИТЬ
    }

    //проверка внутри категории
    public void checkSubmenuButtons(CustomerCategories category) {
        submenuButtons.shouldHave(CollectionCondition
                .containExactTextsCaseSensitive(category.expectedButtons));
        //return this; УТОЧНИТЬ
    }
}