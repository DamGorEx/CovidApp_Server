package mcs.Model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class NewsTest {
    private static byte[] imgToTest;

    @BeforeAll
    static void doBeforeAll() throws IOException {
        imgToTest = Files.readAllBytes(Paths.get("/Users/DamianGoraj/Documents/CovidApp/src/test/java/Service/panda.jpg"));
    }

    @Test
    public void addParagraphTest () {
        var news = new News();
        news.addParagraph("This is 1 paragraph");
        news.addParagraph("This is 2 paragraph");

        var newsArr = news.getNews();
        var firstEl =  (News.TextNewsElement) newsArr.get(0);
        var secEl =  (News.TextNewsElement) newsArr.get(1);
        assertEquals("This is 1 paragraph", firstEl.getTextVal());
        assertEquals("This is 2 paragraph", secEl.getTextVal());
    }


}