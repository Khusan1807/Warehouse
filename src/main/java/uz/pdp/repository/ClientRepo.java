package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Client;

public interface ClientRepo extends JpaRepository<Client, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

}
