package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Currency;

public interface CurrencyRepo extends JpaRepository<Currency, Long> {


    boolean existsByName(String name);

}
