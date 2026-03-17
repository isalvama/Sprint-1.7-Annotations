package second_and_third_level;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;

public class JsonFileSaver {

    public static void saveObjectInJsonFile(Object object) {
        if (object.getClass().isAnnotationPresent(ToJsonFile.class)) {
            ToJsonFile annotation = object.getClass().getAnnotation(ToJsonFile.class);
            String outputFileDirectoryPath = annotation.directoryPath();
            try {
                String objInJson = convertObjToJsonStr(object);
                    saveStringInFile(objInJson, outputFileDirectoryPath);
            } catch (RuntimeException | IOException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.out.println("The object passed as parameter does not belong to a class with the ToJsonFile annotation.");
        }
    }

    private static String convertObjToJsonStr(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(object);
            System.out.println("Serialized JSON:");
            System.out.println(jsonString);
            return jsonString;
        } catch (JsonProcessingException jpe){
            throw new RuntimeException("Error serializing to JSON", jpe);
        }
    }

    private static void saveStringInFile(String jsonString, String outputFileDirectoryPath) throws IOException {
        try (FileWriter writer = new FileWriter(outputFileDirectoryPath + "class.json")) {
            writer.write(jsonString);
        } catch (IOException e) {
            throw new IOException("Error in writing file with the object information in JSON in: " + outputFileDirectoryPath, e);
        }
    }
}