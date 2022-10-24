package hexlet.code.formatters;

import hexlet.code.DiffValue;
import hexlet.code.Value;

import java.util.LinkedHashMap;
import java.util.Map;

public class Stylish {

    public static String getOutput(Map<String, Object> diffMap) {
        Map<String, Object> result = new LinkedHashMap<>();
        for (String key : diffMap.keySet()) {
            Value value = (Value) diffMap.get(key);
            switch (DiffValue.valueOf(value.getDiffValue())) {
                case ADDED -> result.put("+ " + key, value.getValue());
                case DELETED -> result.put("- " + key, value.getValue());
                case UNCHANGED -> result.put("  " + key, value.getValue());
                case CHANGED -> {
                    result.put("- " + key, value.getOldValue());
                    result.put("+ " + key, value.getNewValue());
                }
                default -> throw new RuntimeException();
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
