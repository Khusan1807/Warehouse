package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {


}
