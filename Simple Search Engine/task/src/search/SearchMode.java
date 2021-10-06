package search;

import java.util.*;

public abstract class SearchMode {

    public List<Integer> Search(Map<String, List<Integer>> indexedMap, String[] query) throws Exception {
        throw new Exception("Search Method unspecified");
    }

}

class SearchAll extends SearchMode {

    @Override
    public List<Integer> Search(Map<String, List<Integer>> indexedMap, String[] query) throws Exception {
        List<Integer> commonIndexes = new ArrayList<>();
        for (String word : query) {
            if (!indexedMap.containsKey(word)) {
                throw new Exception("No matching people found");
            } else {
                if (commonIndexes.size() == 0) {
                    commonIndexes = indexedMap.get(word);
                } else {
                    commonIndexes.retainAll(indexedMap.get(word));
                }
            }
        }
        return commonIndexes;
    }
}

class SearchAny extends SearchMode {

    @Override
    public List<Integer> Search(Map<String, List<Integer>> indexedMap, String[] query) throws Exception {
        List<Integer> commonIndexes = new ArrayList<>();
        for (String word : query) {
            if (indexedMap.containsKey(word)) {
                for (Integer index : indexedMap.get(word)) {
                    if (!commonIndexes.contains(index)) {
                        commonIndexes.add(index);
                    }
                }
            }
        }
        if (commonIndexes.size() == 0) {
            throw new Exception("No matching people found");
        }
        return commonIndexes;
    }
}

class SearchNone extends SearchMode {

    @Override
    public List<Integer> Search(Map<String, List<Integer>> indexedMap, String[] query) throws Exception {
        List<Integer> commonIndexes = new ArrayList<>();
        List<Integer> uncommonIndexes = new ArrayList<>();
        for (String word : query) {
            for (String key : indexedMap.keySet()) {
                if (word.equals(key)) {
                    for (Integer index : indexedMap.get(key)) {
                        if (!uncommonIndexes.contains(index)) {
                            uncommonIndexes.add(index);
                        }
                    }
                } else {
                    for (Integer index : indexedMap.get(key)) {
                        if (!commonIndexes.contains(index)) {
                            commonIndexes.add(index);
                        }
                    }
                }
            }
        }
        for (Integer index : uncommonIndexes) {
            commonIndexes.remove(index);
        }
        if (commonIndexes.size() == 0) {
            throw new Exception("No matching people found");
        }
        return commonIndexes;
    }
}