package mcs.Model.Repositories;

import mcs.Model.ShopOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTest {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderRepositoryTest(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Test
    void savaOrder() {
        orderRepository.save(new ShopOrder());
        orderRepository.flush();
    }

}