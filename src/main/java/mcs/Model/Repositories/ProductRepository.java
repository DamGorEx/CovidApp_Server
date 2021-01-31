package mcs.Model.Repositories;

import mcs.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("select p from Product p join p.categories pc where pc = ?1")
    List<Product> getProdByCat(Product.Category category);
}
