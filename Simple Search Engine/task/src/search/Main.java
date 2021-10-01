package search;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.*;

public class Main {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        String filePath = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--data")) {
                filePath = args[i + 1];
            }
        }

        File inputFile = new File(filePath);

        List<String> inputs = new ArrayList<>();

        assert filePath != null;
        try (Scanner fileScan = new Scanner(inputFile)) {
            while (fileScan.hasNext()) {
                inputs.add(fileScan.nextLine());
            }
        }

        while (true) {
            showMenu();
            switch (scan.nextLine()) {
                case "1":
                    System.out.println("Enter a name or email to search all suitable people.");
                    String query = scan.nextLine();
                    printSearchResults(inputs, query);
                    break;
                case "2":
                    printAll(inputs);
                    break;
                case "0":
                    System.out.println("Bye!");
                    System.exit(0);
                default:
                    System.out.println("Incorrect option! Try again.");
            }
        }
    }

    public static void printSearchResults(List<String> inputLines, String query) {
        boolean isFound = false;
        for (String line : inputLines) {
            String lowerLine = line.toLowerCase();
            if (lowerLine.contains(query.toLowerCase())) {
                System.out.println(line);
                isFound = true;
            }
        }
        if (!isFound) {
            System.out.println("No matches");
        }
    }

    public static void showMenu() {
        System.out.println("=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit\n");
    }

    public static void printAll(List<String> inputLines) {
        System.out.println();
        System.out.println("=== List of people ===");
        for (String line : inputLines) {
            System.out.println(line);
        }
    }
}
