package search;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] searchInput = scan.nextLine().split(" ");
        String searchWord = scan.next();
        int index = searchFor(searchWord, searchInput);
        System.out.println(index == -1 ? "Not found" : index);

        }


    public static int searchFor(String word, String[] phrase) {
        for (int i = 0; i < phrase.length; i++) {
            if (phrase[i].equals(word)) {
                return i + 1;
            }
        }
        return -1;
    }
}
