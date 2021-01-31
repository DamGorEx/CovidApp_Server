package mcs.Model.Repositories;

import mcs.Model.Product;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static mcs.Model.Product.Category.books;
import static mcs.Model.Product.Category.mask;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@RunWith(SpringRunner.class)
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    public void save() {
        Product p = new Product();
        productRepository.save(p);
        var id = p.getId();
        assertNotNull(id);
    }
    @Test
    public void findByCat() {
        Product p = new Product();
        p.addCategories(mask, books);
        productRepository.saveAndFlush(p);
        var products = productRepository.getProdByCat(Product.Category.mask);
        assertNotEquals(0, products.size());
    }
}