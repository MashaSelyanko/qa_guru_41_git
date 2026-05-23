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
        try (InputStream is = cl.getResourceAsStream("TestsFile.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
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
        try (InputStream is = cl.getResourceAsStream("TestsFile.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith("xls")) {
                    XLS xls = new XLS(zis);
                    String actualValue = xls.excel.getSheetAt(0)
                            .getRow(3)
                            .getCell(2).getStringCellValue();
                    Assertions.assertTrue(actualValue.contains("Jane"));
                }
            }
        }
    }

                @Test
                void csvFileParsingTest () throws Exception {
                    try (InputStream is = cl.getResourceAsStream("TestsFile.zip");
                         ZipInputStream zis = new ZipInputStream(is)) {
                        ZipEntry entry;

                        while ((entry = zis.getNextEntry()) != null) {
                            if (entry.getName().endsWith("csv")) {
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
                }
            }

