package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;

import static java.nio.file.Files.readString;

public class Parser {

    public static Map<String, Object> fileToMap(String filePath) throws Exception {
        Path fullPath = convertPathToFullPath(filePath);
        Map<String, Object> mapOfFile = null;
        if (filePath.endsWith(".json")) {
            mapOfFile = jsonFileToMap(fullPath);
        } else if (filePath.endsWith(".yml") || filePath.endsWith(".yaml")) {
            mapOfFile = yamlFileToMap(fullPath);
        }
        return mapOfFile;
    }

    public static Map<String, Object> jsonFileToMap(Path path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(readString(path), new TypeReference<>() {
        });
    }

    public static Map<String, Object> yamlFileToMap(Path path) throws Exception {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(readString(path), new TypeReference<>() {
        });
    }

    public static Path convertPathToFullPath(String path) {
        Path resultPath = Path.of(path);
        if (path.startsWith("src/")) {
            File file = new File(path);
            resultPath = Path.of(file.getAbsolutePath());
        } else if (!path.startsWith("/")) {
            String resourcesPath = "src/main/resources/";
            File file = new File(resourcesPath);
            resultPath = Path.of(file.getAbsolutePath() + "/" + path);
        }
        if (new File(resultPath.toString()).exists()) {
            return resultPath;
        } else {
            throw new RuntimeException("Unfortunately, file: " + path + " not found :( ");
        }
    }
}
