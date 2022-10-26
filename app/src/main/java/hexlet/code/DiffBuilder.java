package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class DiffBuilder {

    public static final String ADDED = String.valueOf(DiffValue.ADDED);
    public static final String DELETED = String.valueOf(DiffValue.DELETED);
    public static final String CHANGED = String.valueOf(DiffValue.CHANGED);
    public static final String UNCHANGED = String.valueOf(DiffValue.UNCHANGED);

    public static Map<String, Object> getDifferences(Map<String, Object> mapOfFirstFile,
                                                     Map<String, Object> mapOfSecondFile) {
        Set<String> allSortedKeys = keySorter(mapOfFirstFile, mapOfSecondFile);
        Map<String, Object> result = new LinkedHashMap<>();
        for (String key : allSortedKeys) {
            Object valueOfFirstMap = mapOfFirstFile.get(key);
            Object valueOfSecondMap = mapOfSecondFile.get(key);
            if (!mapOfFirstFile.containsKey(key)) {
                result.put(key, new Value(valueOfSecondMap, ADDED));
            } else if (!mapOfSecondFile.containsKey(key)) {
                result.put(key, new Value(valueOfFirstMap, DELETED));
            }
            if (mapOfFirstFile.containsKey(key) && mapOfSecondFile.containsKey(key)) {
                if (Objects.equals(mapOfFirstFile.get(key), mapOfSecondFile.get(key))) {
                    result.put(key, new Value(valueOfFirstMap, UNCHANGED));
                } else {
                    result.put(key, new Value(valueOfFirstMap, valueOfSecondMap, CHANGED));
                }
            }
        }
        return result;
    }

    public static Set<String> keySorter(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        Set<String> sortedKey = new TreeSet<>();
        sortedKey.addAll(firstMap.keySet());
        sortedKey.addAll(secondMap.keySet());
        return sortedKey;
    }
}
