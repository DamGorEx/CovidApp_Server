package mcs.Model.Repositories;

import mcs.Model.Image;
import mcs.Model.News;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class NewsRepositoryTest {

    private NewsRepository newsRepository;

    @Autowired
    public NewsRepositoryTest(NewsRepository nr) {
        this.newsRepository = nr;
    }
    @Test
    public void addImageAndSave () {
        var newNews = new News();
        var img = new Image("testImg", "jpg",  null);
        newNews.addImg(img);
        var newsArr =  newNews.getNews();
//        var firstEl =  (News.ImgNewsElement) newsArr.get(0);
//        assertNotNull(firstEl);
        newsRepository.save(newNews);
        newsRepository.flush();
        var id = newNews.getNewsId(); //should exists after save
        assertNotEquals("", id);
        var newsEl = newsRepository.getOne(id);
        assertNotNull(newsEl);
    }
}
