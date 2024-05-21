package pdf;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class App {
    public static void main(String[] args) {
        String homeDirectory = System.getProperty("user.home");
        String filePath = homeDirectory + "/pdf/geenrated/";
        File in = new File(args[0]);

        try {
            PDDocument pdf = PDDocument.load(in);
            Inverter[] inverters = new Inverter[pdf.getNumberOfPages()];
            int i = 0;
            for(PDPage page : pdf.getPages()){
                PDDocument newPdf = new PDDocument();
                newPdf.addPage(page);
                String p = filePath + i + ".pdf";
                newPdf.save(p);
                newPdf.close();
                Inverter converter = new Inverter(p,i);
                converter.start();
                inverters[i] = converter;
                i++;
            }
            for(Inverter iverter : inverters){
                try{
                    iverter.join();
                }catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
            PDDocument newPdf = new PDDocument();
            int n = pdf.getNumberOfPages(); 
            PDDocument[] pages = new PDDocument[n];
            for(i = 0; i < n; i++){
                String p = "/home/mustafa/pdf/out/" +  i + ".pdf";
                File f = new File(p);
                PDDocument singlePagePdf = PDDocument.load(f);
                f.delete();
                for(PDPage page : singlePagePdf.getPages()){
                    newPdf.addPage(page);
                }
                pages[i] = singlePagePdf;
            }
            System.out.println(newPdf.getNumberOfPages());
            newPdf.save("/home/mustafa/pdf/out/all" +  args[1] +".pdf");
            newPdf.close();
            for(PDDocument p: pages){
                p.close();
            }
            newPdf.close();
            System.out.println("finished");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}