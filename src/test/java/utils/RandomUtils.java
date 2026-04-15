package utils;
import com.github.javafaker.Faker;
import java.security.SecureRandom;
import java.util.Map;
import static java.lang.String.format;

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

    public static String getRandomEmail() {
        return format("%s@%s.com", getRandomString(8), getRandomString(8));
    }


    private static final Map<String, String[]> stateCityMap = Map.of(
            "NCR", new String[]{"Delhi", "Gurgaon", "Noida"},
            "Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"},
            "Haryana", new String[]{"Karnal", "Panipad"},
            "Rajasthan", new String[]{"Jaipur", "Jaiselmer"}
    );           //объявили словарь, где каждому штату соответствует массив городов

     // Метод генерации для рандомного штата
    public static String getRandomState() {
        // все ключи (штаты) превращаем в массив
        String[] states = stateCityMap.keySet().toArray(new String[0]);
        //[0] означает, что java сама вычислит нужный размер массива
        return faker.options().option(states); // Выбирает случайный штат
    }

    // Метод для города на основе выбранного штата
    public static String getRandomCity(String state) {
        String[] cities = stateCityMap.get(state);

        if (cities == null) return ""; // Защита от краша, если штат не найден

        return faker.options().option(cities); // Выбирает случайный город из массива
    }
}


