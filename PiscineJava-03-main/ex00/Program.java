public class Program {
    public static void main(String[] args) throws InterruptedException {
        Integer count = null;
        try {
            count = Integer.parseInt(args[0].substring(args[0].indexOf("=") + 1));
        } catch (Exception e) {System.exit(-1);}

        Thread threadEgg = new Thread(new Printer(count, "Egg"));
        Thread threadHen = new Thread(new Printer(count, "Hen"));

        threadEgg.start();
        threadHen.start();

        threadEgg.join();
        threadHen.join();

        for(int i = 0; i < count; i++) {
            System.out.println(i + " " + "Human");
        }
    }
}
