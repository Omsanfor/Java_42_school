import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Program {
    public static void main(String[] args) throws InterruptedException {
        int arraySize;
        int threadsCount;
        int odd = 0;
        try {
            arraySize = Integer.parseInt(args[0].substring(args[0].indexOf("=") + 1));
            threadsCount = Integer.parseInt(args[1].substring(args[1].indexOf("=") + 1));
        } catch (Exception e) {return;}

        if (arraySize <= 0 || threadsCount <= 0)
            return;

        Random random = new Random();

        int [] array = new int[arraySize];
        for(int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(1000);
        }

        int sum = 0;
        for(int i = 0; i < arraySize; i++) {
            sum += array[i];
        }
        System.out.println("Sum: " + sum);
        int segmentLength = arraySize / threadsCount;
        if (segmentLength * threadsCount != arraySize) {
            odd = arraySize - segmentLength * threadsCount;
        }

        int begin = 0;
        int end = 0;

        List<Thread> listThread = new ArrayList<>(threadsCount);

        for (int i = 0; i < threadsCount; i++) {
            if (i == threadsCount - 1)
                end += segmentLength + odd;
            else
                end += segmentLength;
            listThread.add(new Thread(new CalculateArray(i ,begin, end, array)));
            begin += segmentLength;
        }

        for (Thread thread: listThread) {
            thread.start();
        }

        for (Thread thread: listThread) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(CalculateArray.result);
    }
}
