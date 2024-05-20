package pdf;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class App {
    public static void main(String[] args) {
        String homeDirectory = System.getProperty("user.home");
        String filePath = homeDirectory + "/pdf/generated.pdf";
        PDDocument pdf = new PDDocument();
        PDPage page = new PDPage();
        pdf.addPage(page);
        try {
            pdf.save(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            pdf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Hello World!");
    }
}
