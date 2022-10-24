package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {

    public static Map<String, Object> fileToMap(String content, String fileFormat) throws Exception {
        switch (FileFormat.valueOf(fileFormat)) {
            case JSON -> {
                return jsonFileToMap(content);
            }
            case YAML, YML -> {
                return yamlFileToMap(content);
            }
            default ->
                    throw new RuntimeException(fileFormat + "is incorrect :(");
        }
    }

    public static Map<String, Object> jsonFileToMap(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, new TypeReference<>() {
        });
    }

    public static Map<String, Object> yamlFileToMap(String content) throws Exception {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(content, new TypeReference<>() {
        });
    }
}
