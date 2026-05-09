package fileParsingTests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class FileParsingTests {

    private ClassLoader cl = FileParsingTests.class.getClassLoader();


    //читаем содержимое zip- архива
    @Test
    void pdfFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                getClass().getClassLoader().getResourceAsStream("TestsFile.zip")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith("pdf")) {
                    PDF pdf = new PDF(zis);
                    Assertions.assertTrue(pdf.text.contains("Lorem ipsum dolor sit amet"));
                }
            }
        }
    }

    @Test
    void xlsxFileParsingTest() throws Exception {
        open("https://sample.cat/ru/xls?ysclid=moy8ennjqb803345076");
        File downloaded = $("[href='https://disk.sample.cat/samples/xlsx/sample1.xlsx']").download();
        XLS xls = new XLS(downloaded);
        String actualValue = xls.excel.getSheetAt(0).getRow(3).getCell(2).getStringCellValue();

        Assertions.assertTrue(actualValue.contains("Jane"));
    }

    @Test
    void csvFileParsingTest() throws Exception {
     //   open("https://samplelib.com/ru/sample-csv.html?ysclid=moyha62jsy922599676");
try (InputStream is = cl.getResourceAsStream("sample-simple.csv");
        CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {
    List<String[]> data = csvReader.readAll();
    Assertions.assertEquals(11, data.size());
    Assertions.assertArrayEquals(
            new String[]{"id","first_name","last_name","email","age","city","salary","joined"},
            data.get(0));

}
    }

}


