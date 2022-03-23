package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {


    boolean existsByNameAndCategoryId(String name, Long category_id);

}
