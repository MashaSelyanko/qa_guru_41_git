package fileParsingTests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

public class ParsingJsonFile {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void jsonFileParsingTest() throws Exception {
        try (InputStream is = getClass().getResourceAsStream("/sample-empty-object.json")) {
            JsonNode root = mapper.readTree(is);
            JsonNode array = root.get("features"); //массив
            String string = root.get("brand").asText();
            int number = root.get("year").asInt();

            String array_0 =array.get(0).asText();
            String array_1 = array.get(1).asText();
            String array_2 = array.get(2).asText();

            Assertions.assertEquals("Toyota", string);
            Assertions.assertEquals(2024, number);
            Assertions.assertEquals("GPS", array_0);
            Assertions.assertEquals("Camera", array_1);
            Assertions.assertEquals("Climate Control", array_2);
        }
    }
}