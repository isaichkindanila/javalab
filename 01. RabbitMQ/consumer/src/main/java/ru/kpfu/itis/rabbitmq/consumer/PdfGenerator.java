package ru.kpfu.itis.rabbitmq.consumer;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PdfGenerator {

    private final String path;
    private final String title;

    private final PdfFont boldFont;
    private final PdfFont regularFont;

    public PdfGenerator(String path, String title) {
        this.path = path;
        this.title = title;

        if (!path.isEmpty()) {
            new File(path).mkdirs();
        }

        try {
            boldFont = PdfFontFactory.createFont("./fonts/NotoSans-Bold.ttf", "Identity-H", true);
            regularFont = PdfFontFactory.createFont("./fonts/NotoSans-Regular.ttf", "Identity-H", true);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @SneakyThrows
    public void generatePdf(Info info) {
        String id = UUID.randomUUID().toString();
        String pdfPath = (path.isEmpty() ? id : path + File.separator + id) + ".pdf";

        PdfWriter pdfWriter = new PdfWriter(pdfPath);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);

        Text heading = new Text(title);
        heading.setFont(boldFont);
        heading.setFontSize(20);
//        heading.setTextAlignment(TextAlignment.CENTER);

        Text blahBlah = new Text("Бла-бла-бла, бла-бла, бла-бла-бла-бла...");
        blahBlah.setFont(regularFont);

        Text infoText = new Text(info.getLastName() +
                ' ' +
                info.getFirstName() +
                ", возраст " +
                info.getAge() +
                ", паспорт " +
                info.getId());
        infoText.setFont(regularFont);

        document.add(new Paragraph(heading).setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph(blahBlah));
        document.add(new Paragraph(infoText));
        document.add(new Paragraph(blahBlah));

        document.close();
    }
}
