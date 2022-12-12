package product.management.demo.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import product.management.demo.models.Product;
import product.management.demo.repositories.ProductRepository;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    @RequestMapping("{id}")
    public Product getProduct(@PathVariable Integer id) {
        return productRepository.getOne(id);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody final Product product) {
        return productRepository.saveAndFlush(product);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable Integer id) {
        productRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        Product existingProduct = productRepository.getOne(id);
        BeanUtils.copyProperties(product, existingProduct, "id");
        return productRepository.saveAndFlush(existingProduct);
    }
}
