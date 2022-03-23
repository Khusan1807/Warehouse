package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Supplier;

public interface SupplierRepo extends JpaRepository<Supplier, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

}
