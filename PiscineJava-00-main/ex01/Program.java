import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int steps = 0;
        int temp;
        boolean isPrime = true;
        if (number <= 1) {
            System.out.println("Illegal Argument");
            System.exit(-1);
        } else if (number == 2 || number == 3) {
            System.out.println("true " + 1);
        } else {
            for (int i = 3; i * i <= number; i += 2){
                steps++;
                temp = number % i;
                if (temp == 0)
                {
                    isPrime = false;
                    break;
                }
            }
            System.out.println(isPrime + " " + steps);
        }
    }
}
