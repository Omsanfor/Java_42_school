import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class Program {
    private Map<String, String> signatures;
    private static final File SIGFILE = new File("signatures.txt");
    private static final String RESFILE = "result.txt";
    private String fileName;
    private BufferedReader reader;
    BufferedWriter writer;
    public Program() {
        signatures = new HashMap<>();
    }

    private void initSigFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(SIGFILE.getAbsolutePath()));
        while(reader.ready()) {
            String line = reader.readLine();
            String key = line.substring(0, line.indexOf(" ") - 1);
            String value = line.substring(line.indexOf(" ") + 1);
            signatures.put(key, value);
        }
        reader.close();
    }

    public void exec() throws IOException {
        initSigFile();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            fileName = scanner.nextLine();
            if (fileName.equals("42")) {
                scanner.close();
                break;
            }
            readFile();
        }
    }

    private void readFile() throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), Charset.forName("ISO-8859-15")));
        String line;
        if (reader.ready()) {
            line = reader.readLine();
            for (Map.Entry<String, String> entry : signatures.entrySet()) {
                if (line.indexOf(entry.getKey()) >= 0) {
                    System.out.println("PROCESSED");
                    writeResult(entry.getKey());
                    reader.close();
                    return;
                }
            }
            System.out.println("UNDEFINED");
            reader.close();
        }
    }

    private void writeResult(String value) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(RESFILE, true));
        writer.write(value);
        writer.newLine();
        writer.close();
    }
}
