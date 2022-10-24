package hexlet.code.formatters;


import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Json {
    public static String getOutput(Map<String, Object> diffMap) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.setSerializationInclusion(Include.NON_NULL)
                //.enable(SerializationFeature.INDENT_OUTPUT)
                .writeValueAsString(diffMap);
    }
}
