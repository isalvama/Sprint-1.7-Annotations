package second_level;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;

public class JsonFileSaver {

    public static void saveObjectInJsonFile(Object object) {
        if (object.getClass().isAnnotationPresent(ToJsonFile.class)) {
            ToJsonFile annotation = object.getClass().getAnnotation(ToJsonFile.class);
            String outputFileDirectoryPath = annotation.directoryPath(); // TODO
            try {
                String objInJson = convertObjToJson(object);
                try {
                    saveStringInFile(objInJson, outputFileDirectoryPath);

                } catch (JsonProcessingException jpe) {
                    System.err.println("Error: " + jpe.getMessage());
                }
            } catch (IOException ioe) {
                System.err.println("Error: " + ioe.getMessage());
            }
        }
    }

    private static String convertObjToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(object);
        System.out.println("Serialized JSON:");
        System.out.println(jsonString);
        return jsonString;
    }
    private static void saveStringInFile(String jsonString, String outputFileDirectoryPath) throws IOException {
        try (FileWriter writer = new FileWriter(outputFileDirectoryPath + "class.json")) {
            writer.write(jsonString);
            System.out.println("Successfully written JSON to file 'class.json'");
        } catch (IOException e) {
            throw new IOException("Error in writing file with object information in json");
        }
    }
}