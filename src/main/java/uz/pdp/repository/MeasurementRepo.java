package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Measurement;

public interface MeasurementRepo extends JpaRepository<Measurement, Long> {


    boolean existsByName(String name);

}
