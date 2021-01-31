package mcs.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public @Data class ShopOrder {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private ShipMethod shipMethod;
    private String address;
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.New;

    @ManyToOne
    @Cascade(CascadeType.ALL)
    @JsonIgnore
    private User user;

    @Cascade(CascadeType.ALL)
    @OneToOne
    private ItemGroup basket;

    public ShopOrder (User user, ItemGroup itemGroup) {
        this.user = user;
        this.basket = itemGroup;
        user.addOrder(this);
    }
    public ShopOrder() {};

    public Double getAmount () {
        return this.basket.getItemList().stream()
                .reduce(0d, (d, itmG) -> d + (itmG.getPrice() * itmG.getQty()), Double::sum);
    }
    private enum OrderStatus {
        New, Finalize;
    }
}
