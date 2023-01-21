import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
    private BlockingQueue<String> queue;
    private int count;

    public Consumer(BlockingQueue<String> queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    @Override
    public void run() {
        String temp = null;
        try {
            for(int i = 0; i < count; i++) {
                queue.take();
                System.out.println("Hen");

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
