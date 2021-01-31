package mcs.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopOrderTest {

    @Test
    void getOrderAccount () {
        Product p = new Product("aaa", "bbb", 20d);
        Product p1 = new Product("bbb", "ccc", 15d);

        ItemGroup itemGroup = new ItemGroup();
        itemGroup.addItem(p, 2);
        itemGroup.addItem(p1, 1);

        User user = new User("Damian", "Boom","randomAdress","123", "damian@wp.pl", "123");
        ShopOrder order = new ShopOrder(user, itemGroup);

       var amnt =  order.getAmount();

       assertEquals(55d, amnt);
    }

}