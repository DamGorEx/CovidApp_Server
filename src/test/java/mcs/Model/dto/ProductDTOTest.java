package mcs.Model.dto;

import mcs.Model.Product;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class ProductDTOTest {

    @Test
    void remapProductDTO_to_Product() {
        ProductDTO dto = new ProductDTO();
        dto.setDesc("aa");
        dto.setName("name");
        dto.setImgId("123");
        dto.setPrice(23d);
        dto.setCategories(Arrays.asList("images/mask", "books"));
        Product pr = new Product(dto);
    }

}