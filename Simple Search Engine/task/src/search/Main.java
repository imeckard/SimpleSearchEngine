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

        assert filePath != null;
        File inputFile = new File(filePath);

        List<String> inputs = new ArrayList<>();

        try (Scanner fileScan = new Scanner(inputFile)) {
            while (fileScan.hasNext()) {
                inputs.add(fileScan.nextLine());
            }
        }

        Map<String, List<Integer>> indexedMap = indexByWord(inputs);

        while (true) {
            showMenu();
            switch (scan.nextLine()) {
                case "1":
                    System.out.println("Select a matching strategy: ALL, ANY, NONE");
                    System.out.println("Enter a name or email to search all suitable people.");
                    String query = scan.nextLine().toLowerCase();
                    printSearchResults(indexedMap, inputs, query);
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

    public static void printSearchResults(Map<String, List<Integer>> indexedMap, List<String> inputs, String query) {
        if (indexedMap.containsKey(query)) {
            System.out.println(indexedMap.get(query).size() + " persons found:");
            for (Integer x : indexedMap.get(query)) {
                System.out.println(inputs.get(x));
            }
        } else {
            System.out.println("No matching people found");
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

    public static Map<String, List<Integer>> indexByWord(List<String> inputs) {
        Map<String, List<Integer>> indexedMap = new TreeMap<>();
        for (int i = 0; i < inputs.size(); i++) {
            String[] tempString = inputs.get(i).toLowerCase().split(" ");
            for (int k = 0; k < tempString.length; k++) {
                if (!indexedMap.containsKey(tempString[k]) || indexedMap.get(tempString[k]).contains(i)) {
                    indexedMap.putIfAbsent(tempString[k], new ArrayList<>());
                }
                indexedMap.get(tempString[k]).add(i);
            }
        }
        return indexedMap;
    }

    public static void printSearchResults(List<String> inputs, List<Integer> indexes) {
        for (Integer index : indexes) {
            System.out.println(inputs.get(index));
        }

    }
}
