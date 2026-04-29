package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AllureEnvironmentWriter {

    public static void createEnvironmentFile() {

        Properties props = new Properties();
        props.setProperty("Browser", ConfigReader.get("browser"));
        props.setProperty("Environment", "Production");
        props.setProperty("BaseURL", ConfigReader.get("baseUrl"));
        props.setProperty("Tester", "Kavitha Jagatheeswaran");

        try (FileOutputStream output = new FileOutputStream("allure-results/environment.properties")) {
            props.store(output, "--------- Test Execution Environment Details ---------");
            System.out.println("✅ environment.properties created successfully with header.");
        } catch (IOException e) {
            System.err.println("❌ Failed to create environment.properties: " + e.getMessage());
        }
    }
}
