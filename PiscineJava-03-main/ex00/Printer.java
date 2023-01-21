public class Printer implements Runnable{
    private int quantity;
    private String whatPrint;

    public Printer(int quantity, String whatPrint) {
        this.quantity = quantity;
        this.whatPrint = whatPrint;
    }

    @Override
    public void run() {
        for (int i = 0; i < quantity; i++) {
            System.out.println(i + " " + whatPrint);
        }

    }
}
