package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Json {
    public static String jsonOutput(Map<String, Object> mapOfFirstFile,
                                    Map<String, Object> mapOfSecondFile,
                                    Set<String> allSortedKeys) throws JsonProcessingException {
        Map<String, Object> map = new LinkedHashMap<>();
        for (String key : allSortedKeys) {
            if (!mapOfFirstFile.containsKey(key)) {
                map.put("+ " + key, mapOfSecondFile.get(key));
            } else if (!mapOfSecondFile.containsKey(key)) {
                map.put("- " + key, mapOfFirstFile.get(key));
            }
            if (mapOfFirstFile.containsKey(key) && mapOfSecondFile.containsKey(key)) {
                if (Objects.equals(mapOfFirstFile.get(key), mapOfSecondFile.get(key))) {
                    map.put("  " + key, mapOfFirstFile.get(key));
                } else {
                    map.put("- " + key, mapOfFirstFile.get(key));
                    map.put("+ " + key, mapOfSecondFile.get(key));
                }
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(map);
    }
}
