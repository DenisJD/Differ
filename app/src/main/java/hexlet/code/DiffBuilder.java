package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class DiffBuilder {

    public static Map<String, Object> getDifferences(Map<String, Object> mapOfFirstFile,
                                                     Map<String, Object> mapOfSecondFile) {
        Set<String> allSortedKeys = Utils.keySorter(mapOfFirstFile, mapOfSecondFile);
        Map<String, Object> result = new LinkedHashMap<>();
        for (String key : allSortedKeys) {
            Object valueOfFirstMap = mapOfFirstFile.get(key);
            Object valueOfSecondMap = mapOfSecondFile.get(key);
            if (!mapOfFirstFile.containsKey(key)) {
                result.put(key, new Value(valueOfSecondMap, Utils.ADDED));
            } else if (!mapOfSecondFile.containsKey(key)) {
                result.put(key, new Value(valueOfFirstMap, Utils.DELETED));
            }
            if (mapOfFirstFile.containsKey(key) && mapOfSecondFile.containsKey(key)) {
                if (Objects.equals(mapOfFirstFile.get(key), mapOfSecondFile.get(key))) {
                    result.put(key, new Value(valueOfFirstMap, Utils.UNCHANGED));
                } else {
                    result.put(key, new Value(valueOfFirstMap, valueOfSecondMap, Utils.CHANGED));
                }
            }
        }
        return result;
    }
}
