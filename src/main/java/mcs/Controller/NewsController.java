package mcs.Controller;


import mcs.Model.News;
import mcs.Model.dto.ParagraphDTO;
import mcs.Model.Repositories.NewsRepository;
import mcs.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("news")
@CrossOrigin(origins = "http://localhost:3000")
public class NewsController {

    private final NewsRepository newsRepository;
    private final ImageService imageService;

    @Autowired
    public NewsController(NewsRepository newsRepository, ImageService imageService) {
        this.newsRepository = newsRepository;
        this.imageService = imageService;
    }

    @GetMapping("all")
    public List<News> getNewsList() {
        return newsRepository.findAll();
    }

    @GetMapping("{Id}")
    News getNewsById(@PathVariable("Id") String id) {
        return newsRepository.getOne(id);
    }
    @PostMapping("create")
    String createEmptyNews () {
        var news = new News();
        return newsRepository.saveAndFlush(news).getNewsId();
    }
    @PostMapping("addPar/{Id}")
    ResponseEntity<News> addpar(@PathVariable("Id") String id, @RequestBody ParagraphDTO paragraphDTO) {
        Optional<News> news = newsRepository.findById(id);
        if (news.isEmpty()) return ResponseEntity.unprocessableEntity().build();
        var newsObj = news.get();
        newsObj.addParagraph(paragraphDTO.getText());
        newsRepository.saveAndFlush(newsObj);
        return ResponseEntity.ok(newsObj);
    }
    @PostMapping("addImg/{Id}")
    ResponseEntity<News> addPhoto(@PathVariable("Id") String id, @RequestParam("file") MultipartFile multipartFile) {
        Optional<News> news = newsRepository.findById(id);
        if (news.isEmpty()) return ResponseEntity.unprocessableEntity().build();
        var newsRec = news.get();
        try {
            newsRec.addImg(imageService.saveImage(multipartFile));
            newsRepository.saveAndFlush(newsRec);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
        return ResponseEntity.ok(newsRec);
    }
}
