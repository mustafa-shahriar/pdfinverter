package pdf;

import java.io.IOException;

public class Inverter extends Thread {
   public String path; 
   public int i;

   public Inverter(String path, int i){
        this.path = path;
        this.i = i;
   }

    @Override
    public void run(){
        String[] command = {"java", "-jar", "/home/mustafa/pdf/PDFInverter.jar", path, "/home/mustafa/pdf/out/" + i + ".pdf"};
        try {
            // Execute the command
            Process process = Runtime.getRuntime().exec(command);

            // Wait for the process to complete
            int exitCode = process.waitFor();
            System.out.println("JAR file executed with exit code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
