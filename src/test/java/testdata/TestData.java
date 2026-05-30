package testdata;

import com.github.javafaker.Faker;
import java.util.Locale;

public class TestData {
    Faker fakerRu = new Faker(new Locale("ru"));
    Faker fakerEn = new Faker(new Locale("en"));


    public String fullName = fakerRu.name().fullName();
    public String dateBirthday = new java.text.SimpleDateFormat("dd.MM.yyyy")
            .format(fakerRu.date().birthday(18, 65));
    public String userNumber = fakerRu.phoneNumber().subscriberNumber(10);
    public String userEmail = fakerEn.internet().emailAddress();

    public String fullNameNegative = fakerEn.name().fullName();

}

