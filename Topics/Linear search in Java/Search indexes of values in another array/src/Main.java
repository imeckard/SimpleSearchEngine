import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static int[] searchIndexes(int[] first, int[] second) {
        int[] indexes = new int[first.length];
        for (int i = 0; i < first.length; i++) {
            for (int k = 0; k < second.length; k++) {
                if (first[i] == second[k]) {
                    indexes[i] = k;
                    break;
                } else if (k == second.length - 1) {
                    indexes[i] = -1;
                }
            }
        }
        return indexes;
    }

    /* Do not change code below */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int[] first = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        final int[] second = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        final String result = Arrays.toString(searchIndexes(first, second))
                .replace(", ", " ")
                .replace("[", "")
                .replace("]", "");
        System.out.println(result);
    }
}