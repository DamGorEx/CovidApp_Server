package mcs.Model;

import mcs.Model.Repositories.ImageRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ImageTest {
    private final ImageRepository imagerepository;

    @Autowired
    public ImageTest(ImageRepository repository) {
        this.imagerepository = repository;
    }

    @Test
    void saveImage () {
        Image image = new Image("im1", "jpg", new byte[]{});
        Image image1 = new Image("im2", "jpg", new byte[]{});

        assertAll(() -> {
                    assertEquals("im1", image.getName());
                    assertEquals("jpg", image.getContentType());
                    assertNotNull(image.getImageBytes());
                    } );
        imagerepository.saveAll(Lists.newArrayList(image, image1));
        imagerepository.flush();
        Image imtest = imagerepository.findByName("im1aaa");
        assertNull(imtest);
        Image imtest1 = imagerepository.findByName("im1");
        assertEquals("im1", imtest1.getName());
    }

}