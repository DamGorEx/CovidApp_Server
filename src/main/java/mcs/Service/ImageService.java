package mcs.Service;

import mcs.Model.Image;
import mcs.Model.Repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.DeflaterInputStream;
import java.util.zip.InflaterInputStream;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public static byte[] comprese (byte[] bytes) throws IOException {
        try (BufferedInputStream dflInStrm = new BufferedInputStream(new DeflaterInputStream(new ByteArrayInputStream(bytes)))) {
            byte[] pic = dflInStrm.readAllBytes();
            return pic;
        }
    }

    public static byte[] decomprese (byte[] bytes) throws IOException {
        try (BufferedInputStream dflInStrm = new BufferedInputStream(new InflaterInputStream(new ByteArrayInputStream(bytes)))) {
            byte[] pic = dflInStrm.readAllBytes();
            return pic;
        }
    }

    public Image saveImage(MultipartFile multipartFile) throws IOException {
        var imageName = multipartFile.getOriginalFilename();
        var contType = multipartFile.getContentType();
        byte[] img;
            img = multipartFile.getBytes();
            img = ImageService.comprese(img);

        Image image = new Image(imageName, contType, img);
        imageRepository.saveAndFlush(image);
        return image;
    }
}
