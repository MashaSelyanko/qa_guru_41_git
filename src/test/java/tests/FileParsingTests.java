package tests;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import java.io.File;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static com.codeborne.selenide.Selenide.open;

public class FileParsingTests extends TestBase {

    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    @DisplayName("Verify interest rules PDF document content parsing")
    @Tag("Minor")
    public void testPdfContent() throws Exception {

        // открываем страницу сайта
        step("Open archive docs", () -> {
            open("/business/settlement-service/tariffs/achive-docs");
        });

//    //скачиваем и проверяем содержимое pdf
        step("Verify downloaded PDF content contains expected text", () -> {
            try {
                File downloadedFile = $("[href='https://bspb.ru/media/pravila_nachislenia_procentov_na_ostatok_050922_96842746f6.pdf']").download();
                PDF pdf = new PDF(downloadedFile);
                Assertions.assertTrue(pdf.text.toLowerCase().contains("правила начисления процентов"));
            } catch (Exception e) {
                Assertions.fail("Ошибка при скачивании или парсинге PDF: " + e.getMessage(), e);
            }
        });
    }
}