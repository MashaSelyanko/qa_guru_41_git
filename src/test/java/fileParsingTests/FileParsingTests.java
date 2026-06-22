package fileParsingTests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class FileParsingTests {

    private ClassLoader cl = FileParsingTests.class.getClassLoader();

    //читаем содержимое zip- архива
    @Test
    void pdfFileParsingTest() throws Exception {
        boolean found = false;
        try (
                InputStream is = cl.getResourceAsStream("TestsFile.zip");
                ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                found = true;
                if (entry.getName().endsWith("pdf")) {
                    PDF pdf = new PDF(zis);
                    Assertions.assertTrue(pdf.text.contains("Lorem ipsum dolor sit amet"));
                }
            }
        }
        Assertions.assertTrue(found, "PDF файла нет в архиве");
    }


    @Test
    void xlsxFileParsingTest() throws Exception {
        boolean found = false;
        try (InputStream is = cl.getResourceAsStream("TestsFile.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith("xls")) {
                    found = true;
                    XLS xls = new XLS(zis);
                    String actualValue = xls.excel.getSheetAt(0)
                            .getRow(3)
                            .getCell(2).getStringCellValue();
                    Assertions.assertTrue(actualValue.contains("Jane"),
                            "Не нашел значение"
                    );
                }
            }
        }
    }

    @Test
    void csvFileParsingTest() throws Exception {
        boolean found = false;
        try (InputStream is = cl.getResourceAsStream("TestsFile.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith("csv")) {
                    found = true;
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> data = csvReader.readAll();
                    Assertions.assertEquals(11, data.size());
                    Assertions.assertArrayEquals(
                            new String[]
                                    {"id", "first_name", "last_name", "email", "age", "city", "salary", "joined"},
                            data.get(0)
                    );
                }
            }
        }
        Assertions.assertTrue(found, "CSV файла нет в архиве");
    }
}

