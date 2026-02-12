package pio.daw;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibraryTest {
    Library testLibrary;

    @BeforeEach
    public void loadLibrary() throws IOException, URISyntaxException{
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("test-data/testInput.txt");
        assertNotNull(url, "Test Data file not found.");
        Path expectedPath = Path.of(url.toURI());
        assertDoesNotThrow(() -> {this.testLibrary = Library.fromFile(expectedPath);});
    }

    @Test
    public void getUserListTest(){
        List<String> users = this.testLibrary.getUserList().stream().map(u -> u.getId()).toList();
        List<String> userIDs = List.of(
            "U001",
            "U002",
            "U003"
        );
        for (String id : userIDs) {
            assertTrue(users.contains(id));
        }
    }
    
}
