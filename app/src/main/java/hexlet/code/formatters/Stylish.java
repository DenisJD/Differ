package hexlet.code.formatters;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Stylish {

    public static String stylishOutput(Map<String, Object> mapOfFirstFile,
                                       Map<String, Object> mapOfSecondFile,
                                       Set<String> allSortedKeys) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        for (String key : allSortedKeys) {
            if (!mapOfFirstFile.containsKey(key)) {
                result.put("+ " + key, mapOfSecondFile.get(key));
            } else if (!mapOfSecondFile.containsKey(key)) {
                result.put("- " + key, mapOfFirstFile.get(key));
            }
            if (mapOfFirstFile.containsKey(key) && mapOfSecondFile.containsKey(key)) {
                if (Objects.equals(mapOfFirstFile.get(key), mapOfSecondFile.get(key))) {
                    result.put("  " + key, mapOfFirstFile.get(key));
                } else {
                    result.put("- " + key, mapOfFirstFile.get(key));
                    result.put("+ " + key, mapOfSecondFile.get(key));
                }
            }
        }
        return correctStylishOutput(result);
    }

    public static String correctStylishOutput(Map<String, Object> values) {
        if (values.size() > 0) {
            StringBuilder result = new StringBuilder("{\n");
            for (String key : values.keySet()) {
                result
                        .append("  ")
                        .append(key)
                        .append(": ")
                        .append(values.get(key)).append("\n");
            }
            return result + "}";
        } else {
            return "{}";
        }
    }
}
