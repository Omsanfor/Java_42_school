import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputWeek = scanner.nextLine();
        int week = 1;
        long allMinGrade = 0;

        while(!inputWeek.equals("42") && week <= 18) {
            if (!inputWeek.equals("Week " + week)) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            allMinGrade = getAllMinGrade(scanner, allMinGrade);
            inputWeek = scanner.nextLine();
            week++;
        }
        displayGraph(reverseGrade(allMinGrade), week);
    }

    private static void displayGraph(long allMinGrade, int week) {
        for (int i = 1; i < week; i++) {
            System.out.print("Week ");
            System.out.print(i);
            System.out.print(" ");
            int num = (int)(allMinGrade % 10);
            for (int j = 0; j < num; j++) {
                System.out.print("=");
            }
            System.out.println(">");
            allMinGrade /= 10;
        }
    }

    private static long reverseGrade(long allMinGrade) {
        long res = 0;
        while(allMinGrade != 0) {
            res = res * 10 + (allMinGrade % 10);
            allMinGrade = allMinGrade / 10;
        }
        return res;
    }

    private static long getAllMinGrade(Scanner scanner, long allMinGrade) {
        int min = scanner.nextInt();
        for (int i = 0; i < 4; i++) {
            int temp = scanner.nextInt();
            min = temp < min ? temp : min;
        }
        scanner.nextLine();
        allMinGrade = allMinGrade * 10 + min;
        return allMinGrade;
    }
}


