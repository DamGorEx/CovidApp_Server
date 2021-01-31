package mcs.Model;

import lombok.Data;
import mcs.Model.dto.ProductDTO;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public @Data
class Product {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @Column(unique=true)
    private String name;
    private String descr;
    private Double price;
    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Image image;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "product_category"
            , joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "user_option", nullable = false)
    Set<Category> categories;

    public Product(String name, String descr, Double price) {
        this();
        this.name = name;
        this.descr = descr;
        this.price = price;
    }
    public Product() {
        this.categories = new HashSet<>();
    }

    public Product(ProductDTO product) {
        this();
        this.name = product.getName();
        this.descr = product.getDesc();
        this.price = product.getPrice();
        setCategories(product.getCategories());
    }

    private void setCategories(List<String> categories) {
        categories.stream().forEach( c -> this.categories.add(Category.valueOf(c)));
    }

    public Double getPrice() {
        return Double.valueOf(price);
    }

    public void addCategories(Category ... categories) {
        for (var c: categories) {
            this.categories.add(c);
        }
    }

    public void removeCategories(Category ...  categories) {
        for (var c: categories) {
            this.categories.remove(c);
        }
    }

    public enum Category {
        mask, hygiene_products, suplements, books;
        @Id @GeneratedValue(generator="system-uuid")
        @GenericGenerator(name="system-uuid", strategy = "uuid")
        private String id;

    }
}
