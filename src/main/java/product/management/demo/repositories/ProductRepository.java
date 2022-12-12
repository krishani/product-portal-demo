package product.management.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import product.management.demo.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
