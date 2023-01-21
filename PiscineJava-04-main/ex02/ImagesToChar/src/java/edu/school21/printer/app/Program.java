package edu.school21.printer.app;
import com.beust.jcommander.JCommander;
import edu.school21.printer.logic.ImagesToChar;

import java.io.IOException;
import java.net.URL;


public class Program {
    public static void main(String[] args) {
        URL path = Program.class.getResource("/resources/it.bmp");
        char whitePixel;
        char blackPixel;
        ImagesToChar image;
        try {
            image = new ImagesToChar(path);
            JCommander.newBuilder()
                    .addObject(image)
                    .build()
                    .parse(args);
        } catch (Exception e) {
            System.out.println("Неверные арги!!!");
            return;
        }

        try {
            image.getImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
