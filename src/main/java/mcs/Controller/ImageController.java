package mcs.Controller;

import mcs.Model.Image;
import mcs.Model.Repositories.ImageRepository;
import mcs.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("image")
public class ImageController {

    private ImageService imageService;
    private ImageRepository imageRepository;

    @Autowired
    public ImageController (ImageService imageService, ImageRepository imageRepository) {
        this.imageService = imageService;
        this.imageRepository = imageRepository;
    }

    @GetMapping("ok")
    public ResponseEntity<String> regetImage() {
        return ResponseEntity.ok("OOOOK");
    }
    @PostMapping("save")
    ResponseEntity<String> saveImage (@RequestParam("file") MultipartFile multipartFile) {
        Image img;
        try {
            img = imageService.saveImage(multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
        return ResponseEntity.ok(img.getId());
    }
    @GetMapping("{id}")
    ResponseEntity<byte[]> getImage (@PathVariable String id) {
        Optional<Image> img = imageRepository.findById(id);
        if (img.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        var imgToSend = img.get();
        try {
                var imgBytes = ImageService.decomprese(imgToSend.getImageBytes());
                var contType = imgToSend.getContentType();
                HttpHeaders headers = new HttpHeaders();
                headers.setCacheControl(CacheControl.noCache().getHeaderValue());
                headers.setContentType(MediaType.valueOf(contType));
                ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(imgBytes, headers, HttpStatus.OK);
            return responseEntity;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }
}
