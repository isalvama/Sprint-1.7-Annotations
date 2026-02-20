package second_level;

import java.lang.annotation.Annotation;

public class Main {
    public static void main(String[] args) {
        OnlineWorker worker = new OnlineWorker("Ines", "Salvans", 20, 10);
        JsonFileSaver.saveObjectInJsonFile(worker);

        try{
            Class<OnlineWorker> aClass = OnlineWorker.class;
            Annotation[] annotations = aClass.getAnnotations();

            for (Annotation annotation : annotations){
                if (annotation instanceof ToJsonFile toJsonAnnotation){
                    System.out.println("name: " + toJsonAnnotation.annotationType());
                    System.out.println("value: " + toJsonAnnotation);
                }
            }
        } catch (Exception e) {
            System.err.println("Error: annotation not found");
        }
    }
}
