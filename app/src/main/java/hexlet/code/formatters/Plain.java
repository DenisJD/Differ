package hexlet.code.formatters;

import hexlet.code.DiffValue;
import hexlet.code.Utils;
import hexlet.code.Value;

import java.util.Map;

public class Plain {
    public static String getOutput(Map<String, Object> diffMap) {
        StringBuilder result = new StringBuilder();
        for (String key : diffMap.keySet()) {
            Value value = (Value) diffMap.get(key);
            switch (DiffValue.valueOf(value.getDiffValue())) {
                case ADDED -> result
                        .append("Property '")
                        .append(key)
                        .append("' was added with value: ")
                        .append(Utils.correctPlainValue(value.getValue()))
                        .append("\n");
                case DELETED -> result
                        .append("Property '")
                        .append(key)
                        .append("' was removed")
                        .append("\n");
                case CHANGED -> result
                        .append("Property '")
                        .append(key)
                        .append("' was updated. From ")
                        .append(Utils.correctPlainValue(value.getOldValue()))
                        .append(" to ")
                        .append(Utils.correctPlainValue(value.getNewValue()))
                        .append("\n");
                case UNCHANGED -> result.append("");
                default -> throw new RuntimeException();
            }

        }
        return result.toString().trim();
    }
}
