import java.io.*;
import java.sql.Array;
import java.util.*;

public class Program {
    public static int maxFileSize = 10;
    public static List<String> txtA = new ArrayList<>();
    public static List<String> txtB = new ArrayList<>();
    public static Set<String> dictionary = new HashSet<>();
    public static int [] vectorA;
    public static int [] vectorB;

    public static double squareRoot(long number) {
        double temp;
        double value = number / 2;

        do {
            temp = value;
            value = (temp + (number / temp)) / 2;
        } while ((temp - value) != 0);

        return value;
    }

    private static long getFileSizeMegaBytes(File file) {
        return file.length()/(1024*1024);
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2)
            throw new Exception("Number of arguments greater than 2");
        BufferedReader readerA;
        BufferedReader readerB;
        BufferedWriter writer;
        File fileA = new File(args[0]);
        File fileB = new File(args[1]);
        if (fileA.exists()) {
            if (getFileSizeMegaBytes(fileA) > 10) {
                System.out.println("Файл слишком большой");
                return;
            }

        }
        if (fileB.exists()) {
            if (getFileSizeMegaBytes(fileB) > 10) {
                System.out.println("Файл слишком большой");
                return;
            }

        }
        try {
            readerA = new BufferedReader(new FileReader(fileA));
            readerB = new BufferedReader(new FileReader(fileB));

            String line;
            while ((line = readerA.readLine()) != null)
                txtA.addAll(Arrays.asList(line.split(" ")));
            while ((line = readerB.readLine()) != null)
                txtB.addAll(Arrays.asList(line.split(" ")));

            dictionary.addAll(txtA);
            dictionary.addAll(txtB);

            System.out.println(txtA);
            System.out.println(txtB);
            System.out.println(dictionary);

            vectorA = new int [dictionary.size()];
            vectorB = new int [dictionary.size()];

            int index = 0;

            for(String s : dictionary) {

                int i = 0;
                for (String sA : txtA) {
                    if (sA.equals(s))
                        i++;
                }
                vectorA[index] = i;
                i = 0;
                for (String sB : txtB)
                    if (sB.equals(s))
                        i++;
                vectorB[index] = i;
                index++;
            }

            for (int i = 0; i < dictionary.size(); i++) {
                System.out.print(vectorA[i] + " ");
            }
            System.out.println();
            for (int i = 0; i < dictionary.size(); i++) {
                System.out.print(vectorB[i] + " ");
            }
            System.out.println();

            long numerator = 0;
            for (int i = 0; i < dictionary.size(); i++) {
                numerator += vectorA[i] * vectorB[i];
            }
            long denominatorA = 0;
            for (int i = 0; i < dictionary.size(); i++) {
                denominatorA += vectorA[i] * vectorA[i];
            }

            long denominatorB = 0;
            for (int i = 0; i < dictionary.size(); i++) {
                denominatorB += vectorB[i] * vectorB[i];
            }


            double sqrtA;
            double sqrtB;
            if (denominatorA != 0)
                sqrtA = squareRoot(denominatorA);
            else
                sqrtA = 0;

            if (denominatorB != 0)
                sqrtB = squareRoot(denominatorB);
            else
                sqrtB = 0;

            System.out.println("denominatorA = " + denominatorA);
            System.out.println("denominatorB = " + denominatorB);

            double similarity = numerator / (sqrtA * sqrtB);

            System.out.println(String.format("%.3f",similarity));

            writer = new BufferedWriter(new FileWriter("Dictionary.txt"));
            for (String s : dictionary) {
                writer.write(s + " ");
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
