package testdata;

import com.github.javafaker.*;

import static utils.RandomUtils.*;

public class TestData {
    Faker faker = new Faker();

    public String firstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String userEmail = faker.internet().emailAddress();
    public String gender = faker.options().option("Male", "Female", "Other");

    public String userNumber = faker.phoneNumber().subscriberNumber(10);
    public String year = String.valueOf(faker.number().numberBetween(1900, 2100));
    // указываем принудительно String, чтобы перевести число из int в String
    public String month = faker.number().numberBetween(0, 12) + "";
    // +"" - добавляет пустую строку = меняет int на String
    public String day = faker.number().numberBetween(1, 28) + "";
    // +"" - добавляет пустую строку = меняет int на String
    public String role = faker.options().option("Maths", "Physics", "Chemistry", "Biology"
            , "English", "Computer Science", "Economics", "Arts", "History", "Civics");
    public String hobbies = faker. options().option("Sports", "Reading", "Music");
    public String picture = "img.png";
    public String state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    public String city = selectCity(state);
    public String currentAddress = faker.address().fullAddress();


    //для негативных тестов
    public String firstNameNegative = "--";
    public String lastNameNegative = "--";
    public String userNumberNegative = "1111111111";
}

