package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.OutputProduct;

public interface OutputProductRepo extends JpaRepository<OutputProduct, Long> {
}
