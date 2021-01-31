package mcs.Model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.annotations.CascadeType.ALL;

@Entity
public @Data class News {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    String newsId;

    @Cascade(ALL)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<NewsElement> news;

    public News () {
        this.news = new ArrayList<>();
    }

    public void addParagraph(String text) {
        TextNewsElement par = new TextNewsElement(text);
            news.add(par);
    }

    public void addImg(Image img) {
        news.add(new ImgNewsElement(img));
    }

    @Entity
    @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "type")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = TextNewsElement.class, name = "paragraph"),
            @JsonSubTypes.Type(value = ImgNewsElement.class, name = "image")
    })
    @Data
     public static  class NewsElement {
        @Id @GeneratedValue(generator="system-uuid")
        @GenericGenerator(name="system-uuid", strategy = "uuid")
        String id;
    }
    @Data
    @Entity
    static class TextNewsElement extends NewsElement {

        private String textVal;
        public TextNewsElement(String text) {
            this.textVal = text;
        }

        public TextNewsElement() {

        }
    }
    @Data
    @Entity
     static class ImgNewsElement extends NewsElement {

        @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        private Image image;

        public ImgNewsElement(Image img) {
            this.image = img;
        }
        public ImgNewsElement () {

        }
    }
}
