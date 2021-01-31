package mcs.Model.Repositories;

import mcs.Model.ItemGroup;
import mcs.Model.ShopOrder;
import mcs.Model.Product;
import mcs.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ShopOrderRepositoryTest {

    private final OrderRepository orderRepository;

    @Autowired
    public ShopOrderRepositoryTest(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Test
    void saveOrder () {
        Product p = new Product("aaa", "bbb", 20d);
        Product p1 = new Product("bbb", "ccc", 15d);

        ItemGroup itemGroup = new ItemGroup();
        itemGroup.addItem(p, 2);
        itemGroup.addItem(p1, 1);

        User user = new User("Damian", "Boom","randomAdress","123", "damian@wp.pl", "123");
        ShopOrder order = new ShopOrder(user, itemGroup);

        orderRepository.saveAndFlush(order);
    }

}