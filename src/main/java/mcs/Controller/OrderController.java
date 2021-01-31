package mcs.Controller;

import mcs.Model.Repositories.OrderRepository;
import mcs.Model.ShopOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    List<ShopOrder> getOrders(Principal principal) {
        var userName = principal.getName();
        return orderRepository.getMyOrders(userName);
    }
}
