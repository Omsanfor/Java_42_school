package edu.school21.printer.app;
import edu.school21.printer.logic.ImagesToChar;

import java.io.IOException;


public class Program {
    public static void main(String[] args) {
        String path;
        char whitePixel;
        char blackPixel;
        try {
            whitePixel = args[0].charAt(0);
            blackPixel = args[1].charAt(0);
            path =  args[2];
        } catch (Exception e) { return; }
        ImagesToChar image = new ImagesToChar(path, whitePixel, blackPixel);
        try {
            image.getImage();
        } catch (IOException e) {
            System.out.println("Что-то пошло не так");
        }
    }
}
