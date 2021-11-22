import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileManagementTest {
    @Test
    public void test_fileWriter() {
        //Just ensures file writer works
        String test = "testCase.txt";
        FileWriter output = null;
        try {
            output = new FileWriter(String.valueOf(new FileWriter(test)));
            output.write("passed");
        } catch (IOException e) {
            System.out.println("error, test failed");
        }
        finally {
            assertNotNull(output);
        }
    }
}