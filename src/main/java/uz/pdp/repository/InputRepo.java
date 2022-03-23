package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Input;

public interface InputRepo extends JpaRepository<Input, Long> {

}
