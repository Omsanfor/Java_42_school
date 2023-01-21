package edu.school21.printer.app;
import edu.school21.printer.logic.ImagesToChar;

import java.io.IOException;
import java.net.URL;


public class Program {
    public static void main(String[] args) {
        URL path = Program.class.getResource("/resources/it.bmp");
        char whitePixel;
        char blackPixel;
        try {
            whitePixel = args[0].charAt(0);
            blackPixel = args[1].charAt(0);
        } catch (Exception e) { return; }
        ImagesToChar image = new ImagesToChar(path, whitePixel, blackPixel);
        try {
            image.getImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
