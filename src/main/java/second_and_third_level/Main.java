package second_and_third_level;

import java.io.IOException;
import java.lang.annotation.Annotation;

public class Main {
    public static void main(String[] args) {
        OnlineWorker worker = new OnlineWorker("Ines", "Salvans", 20);
        try{
            JsonFileSaver.saveObjectInJsonFile(worker);
        } catch (IOException ioe) {
            System.err.println("Error: " + ioe.getMessage());
            ioe.printStackTrace();
        }

            Class<OnlineWorker> aClass = OnlineWorker.class;
            Annotation[] annotations = aClass.getAnnotations();
            for (Annotation annotation : annotations){
                if (annotation instanceof ToJsonFile toJsonAnnotation){
                    System.out.println("name: " + toJsonAnnotation.annotationType());
                    System.out.println("value: " + toJsonAnnotation);
                }
            }
    }
}
