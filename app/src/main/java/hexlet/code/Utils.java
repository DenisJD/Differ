package hexlet.code;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Utils {

    public static final String ADDED = String.valueOf(DiffValue.ADDED);
    public static final String DELETED = String.valueOf(DiffValue.DELETED);
    public static final String CHANGED = String.valueOf(DiffValue.CHANGED);
    public static final String UNCHANGED = String.valueOf(DiffValue.UNCHANGED);

    public static String getDataFormat(String filepath) {
        int indexOfPoint = filepath.lastIndexOf(".");
        String fileFormat = filepath.substring(indexOfPoint + 1);
        if (indexOfPoint > 0) {
            switch (fileFormat) {
                case "json" -> {
                    return String.valueOf(FileFormat.JSON);
                }
                case "yml" -> {
                    return String.valueOf(FileFormat.YML);
                }
                case "yaml" -> {
                    return String.valueOf(FileFormat.YAML);
                }
                default -> throw new RuntimeException("Unknown data format");
            }
        }
        throw new RuntimeException("Unknown data format");
    }

    public static Set<String> keySorter(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        Set<String> sortedKey = new TreeSet<>();
        sortedKey.addAll(firstMap.keySet());
        sortedKey.addAll(secondMap.keySet());
        return sortedKey;
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

    public static String correctPlainValue(Object value) {
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
