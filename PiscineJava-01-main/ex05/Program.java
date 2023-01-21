import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        Menu menu;
        if (args.length != 1) {
            menu = new Menu("non");
        } else {
            menu = new Menu(args[0]);
        }
        menu.start();
    }
}