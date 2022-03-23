package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Warehouse;

public interface WarehouseRepo extends JpaRepository<Warehouse, Long> {

}
