package mcs.Controller;

import mcs.Model.Product;
import mcs.Model.Repositories.ImageRepository;
import mcs.Model.Repositories.ProductRepository;
import mcs.Model.dto.ProductDTO;
import mcs.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    @Autowired
    public ProductController(ImageRepository imageRepository, ProductRepository productRepository) {
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("all")
    List<Product> getProductList () {
       return productRepository.findAll();
    }
    @PostMapping("add")
    ResponseEntity<Product> addProduct (@RequestBody ProductDTO product) {
        var imgId = product.getImgId();
        var newProduct = new Product(product);
        var imageOptional = imageRepository.findById(imgId);
        if (imageOptional.isEmpty()) return ResponseEntity.notFound().build();
        var image = imageOptional.get();
        newProduct.setImage(image);
        return ResponseEntity.ok(productRepository.saveAndFlush(newProduct));
    }

    @GetMapping("category/{cat}")
    List<Product> getProducsByCat(@PathVariable String cat) {
        return productRepository.getProdByCat(Product.Category.valueOf(cat));
    }
    @GetMapping("category/all")
    Product.Category[] getAllCat() {
        return Product.Category.values();
    }
}
