package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {

    public static Map<String, Object> getMap(String content, String formatType) throws Exception {
        switch (FileFormat.valueOf(formatType)) {
            case JSON -> {
                return parseJson(content);
            }
            case YAML, YML -> {
                return parseYaml(content);
            }
            default ->
                    throw new RuntimeException(formatType + "is incorrect :(");
        }
    }

    public static Map<String, Object> parseJson(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, new TypeReference<>() {
        });
    }

    public static Map<String, Object> parseYaml(String content) throws Exception {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(content, new TypeReference<>() {
        });
    }
}
