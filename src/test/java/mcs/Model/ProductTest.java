package mcs.Model;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    public void addRemoveCategories () {
        Product p = new Product("Maseczka na twarz", "Czarna maseczka wielokrotnego użytku", 15d);
        p.addCategories(Product.Category.mask, Product.Category.hygiene_products);
        Set<Product.Category> categories =  p.getCategories();
        assertEquals(2, categories.size());
        p.removeCategories(Product.Category.hygiene_products);
        categories =  p.getCategories();
        assertEquals(1, categories.size());
    }
}