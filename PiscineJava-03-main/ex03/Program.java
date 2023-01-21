import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) throws IOException {
        int threadsCount;
        List<URL> urls = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        try {
            threadsCount =  Integer.parseInt(args[0].substring(args[0].indexOf("=") + 1));
        } catch (Exception e) {return;}

        BufferedReader reader = new BufferedReader(new FileReader(new File("files_urls.txt").getAbsolutePath()));

        while(reader.ready()) {
            try {
                urls.add(new URL(reader.readLine().split(" ")[1]));
            }catch (Exception e) {continue;}
        }
        for (int i = 0; i < threadsCount; i++) {
           threads.add(new Thread(new FileUploader(urls)));
        }
        for(Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Все ссылки обработаны");
    }
}
