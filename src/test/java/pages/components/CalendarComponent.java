package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    private SelenideElement userYearInput = $(".react-datepicker__year-select");
    private SelenideElement userMonthInput = $(".react-datepicker__month-select");
    private String dayLocatorTemplate = (".react-datepicker__day--0%02d:not(.react-datepicker__day--outside-month)");

    public void setDate(String day, String month, String year) {
        userYearInput.selectOption(year);
        int monthValue = Integer.parseInt(month) - 1;
        userMonthInput.selectOptionByValue(String.valueOf(monthValue));
        int dayValue = Integer.parseInt(day);
        String dayLocator = String.format(dayLocatorTemplate, dayValue);
        $(dayLocator).click();
    }
}

