import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int countCoffeeRequest = 0;
        while(number != 42) {
            if (isPrime(getSumOfDigit(number)))
                countCoffeeRequest++;
            number = scanner.nextInt();
        }
        System.out.println("Count of coffee-request – " + countCoffeeRequest);
    }

    public static int getSumOfDigit(int number){
        int result = 0;

        result += number % 10;
        while (number > 10){
            number = number / 10;
            result += number % 10;
        }
        return (result);
    }

    public static boolean isPrime(int number){
        int temp;
        if (number <= 1) {
            return false;
        } else if (number == 2 || number == 3) {
            return true;
        } else {
            for (int i = 3; i * i <= number; i += 2){
                temp = number % i;
                if (temp == 0)
                    return false;
            }
            return true;
        }
    }
}
