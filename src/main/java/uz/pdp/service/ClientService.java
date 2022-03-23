package uz.pdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Client;
import uz.pdp.dto.ApiResponse;
import uz.pdp.repository.ClientRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {


    private final ClientRepo clientRepo;

    public ApiResponse add(Client client) {
        try {
            boolean existsByPhoneNumber = clientRepo.existsByPhoneNumber(client.getPhoneNumber());
            if (existsByPhoneNumber) {
                return new ApiResponse(false, "This phone number already exists!");
            }
            Client client1 = new Client();
            client1.setName(client.getName());
            client1.setPhoneNumber(client.getPhoneNumber());
            clientRepo.save(client1);
            return new ApiResponse(true, "Saved");
        } catch (Exception e) {
            return new ApiResponse(false, "Error on Saving!");
        }
    }

    public ApiResponse update(Long id, Client client) {
        boolean check = false;
        Optional<Client> optionalClient = clientRepo.findById(id);
        if (optionalClient.isPresent()) {
            Client client1 = optionalClient.get();
            client1.setName(client.getName());
            for (Client forEachClient : clientRepo.findAll()) {
                if (forEachClient.getId() != client1.getId() && forEachClient.getPhoneNumber().equals(client.getPhoneNumber())) {
                    check = true;
                    break;
                }
            }
            if (check) {
                return new ApiResponse(false, "This phone number already exists!");
            }
            client1.setPhoneNumber(client.getPhoneNumber());
            clientRepo.save(client1);
            return new ApiResponse(true, "Updated");
        }
        return new ApiResponse(false, "Client not found!");
    }


    public ApiResponse delete(Long id) {
        Optional<Client> optionalClient = clientRepo.findById(id);
        if (!optionalClient.isPresent()) {
            return new ApiResponse(false, "Error on Deleting!");
        }
        clientRepo.delete(optionalClient.get());
        return new ApiResponse(true, "Deleted!");
    }
}
