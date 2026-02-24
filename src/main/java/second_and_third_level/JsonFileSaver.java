package second_and_third_level;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;

public class JsonFileSaver {

    public static void saveObjectInJsonFile(Object object) throws IOException {
        if (object.getClass().isAnnotationPresent(ToJsonFile.class)) {
            ToJsonFile annotation = object.getClass().getAnnotation(ToJsonFile.class);
            String outputFileDirectoryPath = annotation.directoryPath();
            try {
                String objInJson = convertObjToJsonStr(object);
                    saveStringInFile(objInJson, outputFileDirectoryPath);
                } catch (JsonProcessingException jpe) {
                    throw new IOException("Error serializing the object to JSON: ", jpe);
            } catch (IOException ioe) {
                throw new IOException("Error writing the JSON string to file: ", ioe);
            }
        }
    }

    private static String convertObjToJsonStr(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(object);
        System.out.println("Serialized JSON:");
        System.out.println(jsonString);
        return jsonString;
    }

    private static void saveStringInFile(String jsonString, String outputFileDirectoryPath) throws IOException {
        try (FileWriter writer = new FileWriter(outputFileDirectoryPath + "class.json")) {
            writer.write(jsonString);
        } catch (IOException e) {
            throw new IOException("Error in writing file with the object information in JSON in: " + outputFileDirectoryPath, e);
        }
    }
}