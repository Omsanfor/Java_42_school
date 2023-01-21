import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int[] countRepeatLetters = getRepeatCount(input);
        int[] topLetters = getTopRepeatLetters(countRepeatLetters);
        sortTopLetters(topLetters, countRepeatLetters);
        printHistogram(topLetters, countRepeatLetters);
    }
    private static void printHistogram(int[] topLetters, int[] countRepeatLetters) {
		int[] values = new int[10];

		if (countRepeatLetters[topLetters[0]] == 0) {
			return;
		}

		for (int i = 0; i < 10; i++) {
			values[i] = 10 * countRepeatLetters[topLetters[i]] / countRepeatLetters[topLetters[0]];
		}

		for (int i = 10 + 1; i > 0; i--) {
			for (int j = 0; j < 10; j++) {
				if (values[j] + 1 == i && countRepeatLetters[topLetters[j]] != 0) {
					System.out.printf("%4d", countRepeatLetters[topLetters[j]]);
				}
				if (values[j] >= i) {
					System.out.printf("%4s", "#");
				}
			}
			System.out.println();
		}

		for (int i = 0; i < 10; i++) {
			System.out.printf("%4c", (char)topLetters[i]);
		}
	}

    private static void sortTopLetters(int[] topCodeSym, int[] countRepeat) {
        boolean isSorted = false;
        int temp;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < 9; i++) {
                if (countRepeat[topCodeSym[i]] < countRepeat[topCodeSym[i + 1]]) {
                    isSorted = false;
                    temp = topCodeSym[i];
                    topCodeSym[i] = topCodeSym[i + 1];
                    topCodeSym[i + 1] = temp;
                }
            }
        }
    }

    private static int[] getTopRepeatLetters(int[] countRepeatLetters) {
        int[] tops = new int[10];

        int indexMinValue = 0;
        for (int i = 0; i <= 65535; i++) {
            if (countRepeatLetters[i] == 0 || countRepeatLetters[i] < countRepeatLetters[tops[indexMinValue]]) {
                continue;
            }

            if (countRepeatLetters[tops[indexMinValue]] < countRepeatLetters[i]) {
                tops[indexMinValue] = i;
            }

            indexMinValue = 0;
            for (int j = 1; j < 10; j++) {
                if (countRepeatLetters[tops[j]] < countRepeatLetters[tops[indexMinValue]]) {
                    indexMinValue = j;
                } else if (countRepeatLetters[tops[j]] == countRepeatLetters[tops[indexMinValue]]
                        && tops[j] > tops[indexMinValue]) {
                    indexMinValue = j;
                }
            }
        }
        return tops;
    }

    private static int[] getRepeatCount(String input) {
        int[] letters = new int[65535 + 1];
        for (char c : input.toCharArray()) {
            letters[c] = letters[c] + 1;
            if (letters[c] > 999) {
                letters[c] = 999;
            }
        }
        return letters;
    }
}
