package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {
    private static final String STYLISH = "stylish";
    private static final String PLAIN = "plain";
    private static final String JSON = "json";

    public static String getOutput(Map<String, Object> diffMap, String outputStyle) throws JsonProcessingException {
        switch (outputStyle.toLowerCase()) {
            case STYLISH -> {
                return Stylish.getOutput(diffMap);
            }
            case PLAIN -> {
                return Plain.getOutput(diffMap);
            }
            case JSON -> {
                return Json.getOutput(diffMap);
            }
            default ->
                    throw new RuntimeException(outputStyle + "is incorrect style:(");
        }
    }
}
