//package pages;
//
//import com.codeborne.selenide.SelenideElement;
//
//import static com.codeborne.selenide.Condition.text;
//import static com.codeborne.selenide.Selectors.byText;
//import static com.codeborne.selenide.Selenide.$;
//
//public class TextBoxPageResult {
//
//
//
////    private SelenideElement outputResults = $(".table-responsive");
////
////    public void checkResult(String key, String value) {
////        outputResults.$(byText(key))
////                .parent()                    //поднялись к родительской "tr"
////                .$$("td")         //видим все "td"
////                .get(1)                     //берем значение у второго (индекс 1)
////                .shouldHave(text(value));  //проверяем
////    }
////
////    public void checkStateAndCity(String key, String state, String city) {
////        outputResults.$(byText(key))
////                .parent()
////                .$$("td")
////                .get(1)
////                .shouldHave(text(state + " " + city));
////    }
////
////    public void checkSubmit() {
////        com.codeborne.selenide.Selenide.executeJavaScript(
////                "document.querySelector('#closeLargeModal').click()"
////        );
////    }
////}
//
