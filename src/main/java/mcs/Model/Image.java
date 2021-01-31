package mcs.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public @Data class Image {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @Column(unique=true)
    private String name;
    private String contentType;
    @JsonIgnore
    @Column(name = "photo", columnDefinition="BLOB")
    private byte[] imageBytes;

    public Image() {}

    public Image(String name, String contentType, byte[] pic) {
        this.name = name;
        this.contentType = contentType;
        this.imageBytes = pic;
    }
}

