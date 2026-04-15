package testdata;

import com.github.javafaker.Faker;
import static utils.RandomUtils.*;

public class TestData {
    public static Faker faker = new Faker();

    public static String firstName = faker.name().firstName();
    public static String lastName = faker.name().lastName();
    public static String userEmail = getRandomEmail();
    public static String gender = faker.options().option("Male", "Female", "Other");

    public static String userNumber = faker.phoneNumber().subscriberNumber(10);
    public static String year = String.valueOf(faker.number().numberBetween(1900, 2100));
    // указываем принудительно String, чтобы перевести число из int в String
    public static String month = faker.number().numberBetween(0, 12) + "";
    // +"" - добавляет пустую строку = меняет int на String
    public static String day = faker.number().numberBetween(1, 28) + "";
    // +"" - добавляет пустую строку = меняет int на String
    public static String role = faker.options().option("Maths", "Physics", "Chemistry", "Biology"
            , "English", "Computer Science", "Economics", "Arts", "History", "Civics");
    public static String hobbies = faker. options().option("Sports", "Reading", "Music");
    public static String picture = "img.png";
    public static String state = getRandomState();
    public static String city = getRandomCity(state);
    public static String currentAddress = faker.address().fullAddress();


    //для негативных тестов
    public static String firstNameNegative = "--";
    public static String lastNameNegative = "--";
    public static String userNumberNegative = "1111111111";
}

