package mcs.Controller;

import mcs.Model.*;
import mcs.Model.Repositories.OrderRepository;
import mcs.Model.Repositories.ProductRepository;
import mcs.Model.Repositories.UserRepository;
import mcs.Model.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import mcs.Model.dto.OrderDTO;

@RestController
@RequestMapping("orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    private final OrderRepository orderRepository;
    private final ProductRepository pr;
    private final UserRepository ur;
    @Autowired
    public OrderController(OrderRepository orderRepository, ProductRepository pr, UserRepository ur) {
        this.orderRepository = orderRepository;
        this.pr = pr;
        this.ur = ur;
    }

    @GetMapping
    public List<ShopOrder> getOrders(Principal principal) {
        var userName = principal.getName();
        return orderRepository.getMyOrders(userName);
    }

    @GetMapping("shpMethod")
    public ResponseEntity<List<String>> getShipMethods() {

        return ResponseEntity.ok(Stream.of(ShipMethod.values()).map(m -> m.getName()).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<ShopOrder> createOrder(@RequestBody OrderDTO order) {

        User user = ur.findByEmail(order.getUser().getEmail());
        if (user == null) {
            user = new User();
            user.setEmail(order.getUser().getEmail());
            user.setFName(order.getUser().getName());
            user.setLName(order.getUser().getSurname());
        }
        user.setAddress(order.getUser().getFullAdress());
        user.setPhNumber(order.getUser().getNumber());

        ItemGroup itemGroup = new ItemGroup();
        var prodDTOMap = order.getProducts().stream().collect(Collectors.toMap(ProductDTO::getName, ProductDTO::getQty));
        List<Product> prList = pr.getProdByName(new ArrayList<>(prodDTOMap.keySet()));
        prList.forEach((p) -> {
            itemGroup.addItem(p, prodDTOMap.get(p.getName()));
        });
        ShopOrder shopOrder = new ShopOrder(user, itemGroup);
        shopOrder.setShipMethod(ShipMethod.valueOf(order.getShpMethod()));
        return ResponseEntity.ok(orderRepository.saveAndFlush(shopOrder));
    }
}
