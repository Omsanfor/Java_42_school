package edu.school21.printer.logic;

import com.beust.jcommander.Parameter;
import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import com.beust.jcommander.Parameters;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

@Parameters(separators = "=")
public class ImagesToChar {
    private URL image;
    @Parameter(names = {"--white", "-b"}, arity = 1)
    private String whitePixel;
    @Parameter(names = {"--black", "-w"}, arity = 1)
    private String blackPixel;

    public ImagesToChar(URL image) {
        this.image = image;
    }

    public void getImage() throws IOException {
        try {
            Ansi.BColor.valueOf(whitePixel);
            Ansi.BColor.valueOf(blackPixel);
        }catch (Exception e) {
            System.out.println("Неверно задан цвет");
            return;
        }
        BufferedImage imageBuffer = ImageIO.read(image);
        ColoredPrinter cp = new ColoredPrinter.Builder(1, false)
                .foreground(Ansi.FColor.NONE).background(Ansi.BColor.NONE)   //setting format
                .build();
        int [] [] array2D = new int[imageBuffer.getHeight()][imageBuffer.getWidth()];
        for (int x = 0; x < array2D.length; x++) {
            for (int y = 0; y < array2D[x].length; y++) {
                int color = imageBuffer.getRGB(y, x);
                if (color == Color.WHITE.getRGB()) {
                    cp.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(whitePixel));
                }
                else {
                    cp.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(blackPixel));
                }
            }
            System.out.println();
        }
    }
}
