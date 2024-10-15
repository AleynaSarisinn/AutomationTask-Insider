package petstore.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public JsonUtils() {
    }

    public static <T> T readJsonFile(String filePath, Class<T> valueType) throws IOException {
        return objectMapper.readValue(new File(filePath), valueType);
    }

    public static String convertToJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
