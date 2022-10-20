package hexlet.code.formatters;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Plain {
    public static String plainOutput(Map<String, Object> mapOfFirstFile,
                                     Map<String, Object> mapOfSecondFile,
                                     Set<String> allSortedKeys) {
        StringBuilder result = new StringBuilder();
        for (String key : allSortedKeys) {
            if (!mapOfFirstFile.containsKey(key)) {
                result
                        .append("Property '")
                        .append(key)
                        .append("' was added with value: ")
                        .append(correctValue(mapOfSecondFile, key))
                        .append("\n");
            } else if (!mapOfSecondFile.containsKey(key)) {
                result
                        .append("Property '")
                        .append(key)
                        .append("' was removed")
                        .append("\n");
            }
            if (mapOfFirstFile.containsKey(key) && mapOfSecondFile.containsKey(key)) {
                if (!Objects.equals(mapOfFirstFile.get(key), mapOfSecondFile.get(key))) {
                    result
                            .append("Property '")
                            .append(key)
                            .append("' was updated. From ")
                            .append(correctValue(mapOfFirstFile, key))
                            .append(" to ")
                            .append(correctValue(mapOfSecondFile, key))
                            .append("\n");
                }
            }
        }
        return result.toString();
    }

    public static String correctValue(Map<String, Object> mapOfFile, String key) {
        Object value = mapOfFile.get(key);
        if (value == null) {
            return null;
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof Integer) {
            return value.toString();
        } else if (value.equals(false) || value.equals(true)) {
            return value.toString();
        }
        return "[complex value]";
    }
}
