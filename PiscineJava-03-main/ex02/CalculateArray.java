public class CalculateArray implements Runnable{
    private int begin;
    private int end;
    private int [] array;
    public static volatile int result;
    private int id;

    public CalculateArray(int id, int begin, int end, int[] array) {
        this.begin = begin;
        this.end = end;
        this.array = array;
        this.id = id;
    }

    @Override
    public void run() {
        calculate();
        System.out.println("Thread " + id + ": from " + begin + " to " + end + " sum is " + result);
    }

    private void calculate() {
        for (int i = begin; i < end; i ++) {
            synchronized (CalculateArray.class) {
                result += array[i];
            }
        }
    }
}
