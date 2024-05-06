package mcapi.davidout.manager;

import mcapi.davidout.manager.file.json.JsonFileManager;
import mcapi.davidout.manager.file.yaml.YamlFileManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTests {

    private JsonFileManager jsonFileManager;
    private YamlFileManager yamlFileManager;

    @BeforeEach
    public void setUp() {
        jsonFileManager = new JsonFileManager(new File("test-folder"));
        yamlFileManager = new YamlFileManager(new File("test-folder"));
    }

    @AfterEach
    public void tearDown() {
        // Clean up after each test if necessary
    }

    @Test
    public void testJsonFileOperations() throws IOException {
        // Test JSON file operations

        // Create a test object
        TestObject testObject = new TestObject("John", 30);

        // Test saveFile and loadFile methods
        String jsonFilePath = "test-folder/test";
        assertTrue(jsonFileManager.saveFile(testObject, jsonFilePath));
        TestObject loadedObject = jsonFileManager.loadFile(TestObject.class, jsonFilePath);
        assertEquals(testObject, loadedObject);

        // Test deleteFile method
        assertTrue(jsonFileManager.deleteFile(jsonFilePath));
        assertFalse(new File(jsonFilePath).exists());
    }

    @Test
    public void testYamlFileOperations() throws IOException {
        // Test YAML file operations

        // Create a test object
        TestObject testObject = new TestObject("Alice", 25);

        // Test saveFile and loadFile methods
        String yamlFilePath = "test-folder/test";
        assertTrue(yamlFileManager.saveFile(testObject, yamlFilePath));
        TestObject loadedObject = yamlFileManager.loadFile(TestObject.class, yamlFilePath);
        assertEquals(testObject, loadedObject);

        // Test deleteFile method
        assertTrue(yamlFileManager.deleteFile(yamlFilePath));
        assertFalse(new File(yamlFilePath).exists());
    }

    static class TestObject {
        private String name;
        private int age;

        public TestObject() {}

        public TestObject(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestObject that = (TestObject) o;
            return age == that.age && name.equals(that.name);
        }
    }

}
