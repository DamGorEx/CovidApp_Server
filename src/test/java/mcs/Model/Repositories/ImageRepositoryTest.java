package mcs.Model.Repositories;

import mcs.Model.Image;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@DataJpaTest
public class ImageRepositoryTest {

    @Autowired
    private ImageRepository imageRepository;

    @Test
    void contextLoads() throws IOException {

        var fileToTest = Files.readAllBytes(Paths.get("/Users/DamianGoraj/Documents/CovidApp/src/test/java/panda.jpg"));
        var img1 = new Image("Image1", "JPG", fileToTest);
        var img2 = new Image("Image2", "JPG", fileToTest);
        var entitiesToSave = Arrays.asList(img1, img2);
        imageRepository.saveAll(entitiesToSave);
        imageRepository.flush();
        var shouldBeNull = imageRepository.findByName("ImageAAA");
        assertNull(shouldBeNull);
        var shouldBeImg1 = imageRepository.findByName("Image1");
        assertNotNull(shouldBeImg1);
    }
}