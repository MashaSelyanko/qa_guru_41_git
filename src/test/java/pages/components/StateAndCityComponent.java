package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class StateAndCityComponent {
    private SelenideElement userStateCombobox = $("#state");
    private SelenideElement userCityCombobox = $("#city");

    public void setStateAndCity(String state, String city) {
        userStateCombobox.click();
        $(byText(state)).click();
        userCityCombobox.click();
        $(byText(city)).click();
    }


}