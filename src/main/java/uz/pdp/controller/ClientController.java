package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.ApiResponse;
import uz.pdp.entity.Client;
import uz.pdp.repository.ClientRepo;
import uz.pdp.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {


    private final ClientService clientService;
    private final ClientRepo clientRepo;


    @GetMapping("/all")
    public List<Client> get() {
        return clientRepo.findAll();
    }


    @PostMapping("/add")
    public ApiResponse add(@RequestBody Client client) {
        return clientService.add(client);
    }


    @PutMapping("/update/{id}")
    public ApiResponse update(@PathVariable Long id, @RequestBody Client client) {
        return clientService.update(id, client);
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        return clientService.delete(id);
    }

}
