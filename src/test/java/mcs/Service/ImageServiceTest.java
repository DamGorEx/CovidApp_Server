package mcs.Service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class ImageServiceTest {
    static byte[] fileToTest;

    @BeforeAll
    static void doBeforeAll() throws IOException {
        fileToTest = Files.readAllBytes(Paths.get("/Users/domlu/CovidApp_Server/src/test/java/panda.jpg"));
    }

    @Test
    void compreseAndDecomprese() throws IOException {
        var fileSize = fileToTest.length;
        var comprFile = ImageService.comprese(fileToTest);
        var compressedFileSize = comprFile.length;
        assertTrue(compressedFileSize < fileSize);
        var decompressedFile = ImageService.decomprese(comprFile);
        var decomprFileSize = decompressedFile.length;
        assertTrue(decomprFileSize == fileSize);
    }
}