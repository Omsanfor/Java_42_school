import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{
    private BlockingQueue<String> queue;
    private int count;

    public Producer(BlockingQueue<String> queue, int count) {
        this.queue = queue;
        this.count = count;
    }
        @Override
    public void run() {
        String temp = Thread.currentThread().getName();
            try {
                for(int i = 0; i < count; i++) {
                    queue.put(temp);
                    System.out.println("Egg");
                    Thread.sleep(25);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}
