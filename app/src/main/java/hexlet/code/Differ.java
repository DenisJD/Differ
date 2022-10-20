package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String file1, String file2, String outputStyle) throws Exception {
        Map<String, Object> firstMap = Parser.fileToMap(file1);
        Map<String, Object> secondMap = Parser.fileToMap(file2);
        Set<String> allSortedKeys = keySorter(firstMap, secondMap);
        return switch (outputStyle.toLowerCase()) {
            case "stylish" ->
                    Stylish.stylishOutput(firstMap, secondMap, allSortedKeys);
            case "plain" ->
                    Plain.plainOutput(firstMap, secondMap, allSortedKeys);
            case "json" ->
                    Json.jsonOutput(firstMap, secondMap, allSortedKeys);
            default ->
                    "Format \"" + outputStyle + "\" is invalid! Correct formats: \"stylish\", ";
        };
    }

    public static String generate(String file1, String file2) throws Exception {
        return generate(file1, file2, "stylish");
    }

    public static Set<String> keySorter(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        Set<String> sortedKey = new TreeSet<>();
        sortedKey.addAll(firstMap.keySet());
        sortedKey.addAll(secondMap.keySet());
        return sortedKey;
    }
}
