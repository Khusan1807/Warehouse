package uz.pdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dto.ApiResponse;
import uz.pdp.dto.OutputDto;
import uz.pdp.entity.Client;
import uz.pdp.entity.Currency;
import uz.pdp.entity.Output;
import uz.pdp.entity.Warehouse;
import uz.pdp.repository.ClientRepo;
import uz.pdp.repository.CurrencyRepo;
import uz.pdp.repository.OutputRepo;
import uz.pdp.repository.WarehouseRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OutputService {


    private final OutputRepo outputRepo;
    private final WarehouseRepo warehouseRepo;
    private final ClientRepo clientRepo;
    private final CurrencyRepo currencyRepo;

    public ApiResponse getById(Long id) {
        Optional<Output> optionalOutput = outputRepo.findById(id);
        if (optionalOutput.isPresent()) {
            return new ApiResponse(true, "Success", optionalOutput.get());
        }
        return new ApiResponse(false, "Output not Found");
    }


    public ApiResponse addOrUpdate(OutputDto dto) {
        Output output = new Output();
        if (dto.getId() != null) {
            output = outputRepo.getById(dto.getId());
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(dto.getWarehouseId());
        if (!optionalWarehouse.isPresent()) {
            return new ApiResponse(false, "Cannot find Warehouse!");
        }
        Optional<Client> optionalClient = clientRepo.findById(dto.getClientId());
        if (!optionalClient.isPresent()) {
            return new ApiResponse(false, "Cannot find Client");
        }
        Optional<Currency> optionalCurrency = currencyRepo.findById(dto.getCurrencyId());
        if (!optionalCurrency.isPresent()) {
            return new ApiResponse(false, "Cannot find Currency");
        }

        output.setCode(dto.getCode());
        output.setCurrency(optionalCurrency.get());
        output.setClient(optionalClient.get());
        output.setWarehouse(optionalWarehouse.get());
        output.setFactureNumber(dto.getFactureNumber());
        outputRepo.save(output);
        return new ApiResponse(true, dto.getId() != null ? "Edited" : "Saved");
    }


    public ApiResponse delete(Long id) {
        Optional<Output> optionalOutput = outputRepo.findById(id);
        if (!optionalOutput.isPresent()) {
            return new ApiResponse(false, "Error on Deleting!");
        }
        outputRepo.delete(optionalOutput.get());
        return new ApiResponse(true, "Deleted!");

    }


}
