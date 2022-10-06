package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String file1, String file2) throws Exception {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        File firstFile = new File(file1);
        File secondFile = new File(file2);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> firstMap = objectMapper.readValue(firstFile, new TypeReference<>() {
        });
        Map<String, Object> secondMap = objectMapper.readValue(secondFile, new TypeReference<>() {
        });
        Set<String> allSortedKeys = new TreeSet<>();
        allSortedKeys.addAll(firstMap.keySet());
        allSortedKeys.addAll(secondMap.keySet());
        for (String key : allSortedKeys) {
            if (!firstMap.containsKey(key)) {
                result.put("+ " + key, secondMap.get(key));
            } else if (!secondMap.containsKey(key)) {
                result.put("- " + key, firstMap.get(key));
            }
            if (firstMap.containsKey(key) && secondMap.containsKey(key)) {
                if (firstMap.get(key).equals(secondMap.get(key))) {
                    result.put("  " + key, firstMap.get(key));
                } else {
                    result.put("- " + key, firstMap.get(key));
                    result.put("+ " + key, secondMap.get(key));
                }
            }
        }
        System.out.println(correctOutput(result));
        return correctOutput(result);
    }

    public static String correctOutput(Map<String, Object> values) {
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

