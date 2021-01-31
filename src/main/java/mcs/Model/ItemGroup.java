package mcs.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public @Data class ItemGroup {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @OneToMany @Cascade(CascadeType.ALL)
    private List<Item> itemList;

    public ItemGroup () {
        itemList = new ArrayList<>();
    }

    public void addItem(Product product, Integer qty) {
        Optional<Item> item = itemList.stream().filter(i -> i.getProduct().equals(product)).findFirst();
        item.ifPresentOrElse(i -> {
            i.setPrice(product.getPrice());
            i.setQty(i.getQty() + qty);
        }, () -> itemList.add(new Item(product, product.getPrice(), qty)));
    }

    @Entity
    public static @Data class Item {
        @Id @GeneratedValue(generator="system-uuid")
        @GenericGenerator(name="system-uuid", strategy = "uuid")
        private String id;
        @ManyToOne
        @Cascade(CascadeType.ALL)
        private Product product;

        private Double price;
        private Integer qty;

        public Item(Product product, Double price, Integer qty) {
            this.product = product;
            this.price = price;
            this.qty = qty;
        }
        public Item (){};
    }
}
