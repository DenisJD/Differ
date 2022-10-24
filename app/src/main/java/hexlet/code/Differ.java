package hexlet.code;

import java.nio.file.Files;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String outputStyle) throws Exception {
        String contentOfFirstFile = Files.readString(Utils.convertPathToFullPath(filepath1));
        String contentOfSecondFile = Files.readString(Utils.convertPathToFullPath(filepath2));

        String formatOfFirstFile = Utils.getDataFormat(filepath1);
        String formatOfSecondFile = Utils.getDataFormat(filepath2);

        Map<String, Object> firstMap = Parser.fileToMap(contentOfFirstFile, formatOfFirstFile);
        Map<String, Object> secondMap = Parser.fileToMap(contentOfSecondFile, formatOfSecondFile);

        Map<String, Object> diffMap = DiffBuilder.getDifferences(firstMap, secondMap);

        return Formatter.getOutput(diffMap, outputStyle);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
}
