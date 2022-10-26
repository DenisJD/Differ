package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String outputStyle) throws Exception {
        String contentOfFirstFile = Files.readString(convertPathToFullPath(filepath1));
        String contentOfSecondFile = Files.readString(convertPathToFullPath(filepath2));

        String formatOfFirstFile = getDataFormat(filepath1);
        String formatOfSecondFile = getDataFormat(filepath2);

        Map<String, Object> firstMap = Parser.fileToMap(contentOfFirstFile, formatOfFirstFile);
        Map<String, Object> secondMap = Parser.fileToMap(contentOfSecondFile, formatOfSecondFile);

        Map<String, Object> diffMap = DiffBuilder.getDifferences(firstMap, secondMap);

        return Formatter.getOutput(diffMap, outputStyle);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
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
}
