package mcs.Model.Repositories;

import mcs.Model.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<ShopOrder, String> {


    @Query("select ord from ShopOrder ord where ord.user.email=?1")
    List<ShopOrder> getMyOrders(String userName);

}
