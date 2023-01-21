import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Path startPath = Paths.get("/");
        try {
            if (args[0].startsWith("--current-folder="))
                startPath = Paths.get(args[0].substring(17));
        } catch (Exception e) {
            System.out.println("start with --current-folder=");
            return ;
        }
        MiniShell mini = new MiniShell(startPath);
        Scanner scan = new Scanner(System.in);
        String inputLine;
        while ((inputLine = scan.nextLine()) != null) {
            try {
                String[] cmd = inputLine.split(" ");
                switch (cmd[0]) {
                    case "ls":
                        mini.ls();
                        break;
                    case "cd":
                        mini.cd(Paths.get(cmd[1]));
                        break;
                    case "mv":
                        mini.mv(Paths.get(cmd[1]), Paths.get(cmd[2]));
                        break;
                    case "exit":
                        scan.close();
                        System.exit(0);
                    default:
                        System.out.println("Unknown command. Type \"exit\" to quit the program");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
