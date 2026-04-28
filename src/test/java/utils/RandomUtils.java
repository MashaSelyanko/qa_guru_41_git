package utils;

import com.github.javafaker.*;

import java.security.*;
import java.util.*;

public class RandomUtils {

    static Faker faker = new Faker();

    public static String getRandomString(int length) {
        String LETTERS = "ABCDEFGHIJQRSTUVWXYZabcdefghlmnovwxyz";
        StringBuilder result = new StringBuilder();
        SecureRandom rnd = new SecureRandom();

        for (int i = 0; i < length; i++)
            result.append(LETTERS.charAt(rnd.nextInt(LETTERS.length())));

        return result.toString();
    }

    public static String getRandomState() {
        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }


}