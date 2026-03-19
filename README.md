# Sprint 1.7: Java Annotations

## Description

This project was created for academic purposes as part of the IT ACADEMY Java & Spring specialization. The primary objective is to understand and implement Java **Annotations**.

The project is divided into levels:
- **First Level**: Focused on built-in annotations (`@Override`, `@Deprecated`, and `@SuppressWarnings`).
- **Second & Third Level**: Focused on the creation of custom annotations and their processing at runtime using **Reflection** and **Jackson** for JSON serialization.


## Techonologies

- Java 17+
- Gradle
- Jackson Databind (for JSON processing)


## Project Structure

```
## Project Structure
TascaS1.01/
├── src/
└── main/
     └── java/
          ├── first_level/
          │     ├── Worker.java
          │     ├── OnlineWorker.java
          │     ├── OfficeBasedWorker.java
          │     └── Main.java
          └── second_and_third_level/
                ├── Worker.java
                ├── OnlineWorker.java
                ├── ToJsonFile.java (Custom Annotation)
                ├── JsonFileSaver.java
                └── Main.java
```

## Installation & Configuration

1. **Clone the repository**:
   ```bash
   git clone https://github.com/isalvama/tascaS1.01.git

2. **Add Jackson Dependency: This project requires the Jackson library for JSON processing. Ensure your build.gradle includes**:
    ```bash
   Gradle 
   dependencies {
   implementation 'com.fasterxml.jackson.core:jackson-databind:2.15.2'
   }
   
3. **Build the project**:
     ```bash
   ./gradlew build


## Concepts covered
Polymorphism and Method Overriding: Using @Override to ensure correct inheritance.
Legacy Code Management: Using @Deprecated to phase out obsolete methods and providing documentation for new alternatives.
Warning Management: Using @SuppressWarnings to handle legacy calls or specific compiler messages.
Custom Annotation Development: Creating @interface definitions with parameters.
Meta-annotations: Utilizing @Target and @Retention to control annotation scope and lifecycle.
Reflection API: Dynamically inspecting classes at runtime to detect and process annotations.
JSON Serialization: Automating data persistence through metadata-driven logic.


## First Level: Built-in Annotations

### The Worker Hierarchy
A base abstract class Worker defines the basic attributes (name, surname, price per hour). Two specialized classes inherit from it:

#### OnlineWorker:
- **@Override**: Redefines calculateSalary to include a fixed internet rate.
- **@Deprecated**: The method calculateBasicSalary is marked as obsolete because it doesn't include the internet bonus. Users are encouraged via Javadoc to use the new calculateSalary method.

#### OfficeBasedWorker:
- **@Override**: Redefines calculateSalary to include gas expenses.
- **@Deprecated**: The setGasPrice method is deprecated to enforce stricter business rules. Instead, the class provides increaseGasPrice and decreaseGasPrice methods which include input validation (throwing IllegalArgumentException if the price change is too high or negative).

##### Warning Suppression
In the Main class, the @SuppressWarnings("deprecation") annotation is used at the method level. This demonstrates how a developer can consciously decide to use legacy methods without the compiler flooding the console with warnings.


## Second & Third Level: Custom Annotations & Reflection
This section implements a more complex, metadata-driven system for data persistence.

### Custom Annotation: @ToJsonFile
We created a custom annotation that marks a class for automatic JSON serialization.

- **Retention**: Set to RUNTIME, allowing the Java Reflection API to see it while the program is executing.
- **Target**: Set to TYPE_USE, meaning it can be applied to classes or other type declarations.
- **Parameter**: Includes a directoryPath() element to specify where the JSON file should be saved.

### The JSON Saving Engine (JsonFileSaver)
This utility class is the "processor" for our custom annotation:
- **Annotation Detection**: It checks if an object's class has the @ToJsonFile annotation using isAnnotationPresent().
- **Metadata Extraction**: If present, it retrieves the directoryPath value from the annotation instance.
- **Jackson Serialization**: It uses ObjectMapper to convert the Java object into a JSON string.
- **File Persistence**: It writes the serialized string into a file named class.json at the specified directory.

### Advanced Polymorphic Serialization
The abstract Worker class in this level is annotated with Jackson's @JsonTypeInfo and @JsonSubTypes. This ensures that even when treating objects as the generic Worker type, the JSON output contains a _type property that identifies the actual implementation class (e.g., OnlineWorker).
Testing the System
The Main class instantiates an OnlineWorker, marked with our custom @ToJsonFile annotation. It triggers the JsonFileSaver and finally uses Reflection to iterate through all annotations of the class, printing their names and values to confirm that the system recognizes the metadata correctly.
