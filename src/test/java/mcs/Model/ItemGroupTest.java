package mcs.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemGroupTest {
    ItemGroup itemGroup;
    Product product;

    @BeforeEach
    public void before() {
        itemGroup = new ItemGroup();
        product = new Product("Test product", "some product", 23d);
    }

    @Test
    void addItem() {
        itemGroup.addItem(product, 3);
        assertEquals(1, itemGroup.getItemList().size());
        product.setPrice(15d);
        itemGroup.addItem(product, 3);
        var expectedPrice = itemGroup.getItemList().get(0).getPrice();
        var expectedQty = itemGroup.getItemList().get(0).getQty();
        assertAll(
                () -> assertEquals(expectedPrice, 15d),
                () -> assertEquals(expectedQty, 6)
        );
        itemGroup.addItem(new Product("Test product", "some product", 23d), 3);
        assertEquals(2, itemGroup.getItemList().size());
    }
}