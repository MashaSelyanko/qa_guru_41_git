package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static com.codeborne.selenide.Selenide.open;
import pages.TextBoxPage;

//@Story("BankWebMain")
public class WebMain {
    TextBoxPage textBoxPage = new TextBoxPage();

    static Stream<Arguments> customerCategoryProvider() {
        return Stream.of(
                Arguments.of(CustomerCategories.PHYSICAL),
                Arguments.of(CustomerCategories.LEGAL),
                Arguments.of(CustomerCategories.FEA),
                Arguments.of(CustomerCategories.MARKETS),
                Arguments.of(CustomerCategories.INVESTORS)
        );
    }
        @DisplayName("Header Tests")
        @MethodSource("customerCategoryProvider")
        @ParameterizedTest(name = "Проверка хэдера для: {0}")
        @Tag("Blocker")
        void successfulCustomerCategoriesTest(CustomerCategories category) {
            open("https://www.bspb.ru/");

            textBoxPage.selectCategory(category);
            textBoxPage.checkSubmenuButtons(category);
        }


    }

//
//}
//}
//TestData testData = new TestData();
//TextBoxPage textBoxPage = new TextBoxPage();
//TextBoxPageResult textBoxPageResult = new TextBoxPageResult();
//
//@BeforeEach
//void prepareRandomData() {
//    testData.city = selectCity(testData.state);
//}
//
//void addListener() {
//    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
//}               //добавляет скриншоты
//
//@Test
//@DisplayName("Fill and check form tests")
//void fillAndCheckFormTests_with_faker() {
//    step("Open registration form", () -> {
//        textBoxPage.openPage();
//        executeJavaScript("document.querySelector('#fixedban')?.remove()");
//        executeJavaScript("document.querySelector('footer')?.remove()");
//    });
//
//    step("Fill registration form", () -> {
//        textBoxPage.typeUserFirstName(testData.firstName);
//        textBoxPage.typeUserLastName(testData.lastName);
//        textBoxPage.typeUserEmail(testData.userEmail);
//        textBoxPage.setGender(testData.gender);
//        textBoxPage.setDateOfBirth(testData.day, testData.month, testData.year);
//        textBoxPage.setUserTelNumberInput(testData.userNumber);
//        textBoxPage.setSubjectInput(testData.role);
//        textBoxPage.setHobbies(testData.hobbies);
//        textBoxPage.setUploadPicture(testData.picture);
//        textBoxPage.setUserAddressTextarea(testData.currentAddress);
//        textBoxPage.setStateAndCity(testData.state, testData.city);
//        textBoxPage.submitForm();
//    });
//
//    step("Check registration form results", () -> {
//        //Tests
//        textBoxPageResult.checkResult("Student Name", testData.firstName + " " + testData.lastName);
//        textBoxPageResult.checkResult("Student Email", testData.userEmail);
//        textBoxPageResult.checkResult("Gender", testData.gender);
//        textBoxPageResult.checkResult("Mobile", testData.userNumber);
//        textBoxPage.checkDate("Date of Birth", testData.day, testData.month, testData.year);
//        textBoxPageResult.checkResult("Subjects", testData.role);
//        textBoxPageResult.checkResult("Hobbies", testData.hobbies);
//        textBoxPageResult.checkResult("Picture", testData.picture);
//        textBoxPageResult.checkResult("Address", testData.currentAddress);
//        textBoxPageResult.checkStateAndCity("State and City", testData.state, testData.city);
//        textBoxPageResult.checkSubmit();
//    });
//
//
//}
