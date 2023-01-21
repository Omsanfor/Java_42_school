import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Program {
    public static void main(String[] args) {
        int count = 0;
        try {
            count = Integer.parseInt(args[0].substring(args[0].indexOf("=") + 1));
        } catch (Exception e) {return ;}


        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(1);
        Consumer consumer = new Consumer(queue, count);
        Producer producer = new Producer(queue, count);
        new Thread(producer, "Egg").start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(consumer, "Hen").start();
    }
}
