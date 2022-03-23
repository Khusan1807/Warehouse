package uz.pdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dto.ApiResponse;
import uz.pdp.dto.InputDto;
import uz.pdp.entity.Currency;
import uz.pdp.entity.Input;
import uz.pdp.entity.Supplier;
import uz.pdp.entity.Warehouse;
import uz.pdp.repository.CurrencyRepo;
import uz.pdp.repository.InputRepo;
import uz.pdp.repository.SupplierRepo;
import uz.pdp.repository.WarehouseRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InputService {


    private final InputRepo inputRepo;
    private final WarehouseRepo warehouseRepo;
    private final SupplierRepo supplierRepo;
    private final CurrencyRepo currencyRepo;
    private final ProductService productService;


    public ApiResponse getById(Long id) {
        Optional<Input> optionalInput = inputRepo.findById(id);
        if (optionalInput.isPresent()) {
            return new ApiResponse(true, "Success", optionalInput.get());
        }
        return new ApiResponse(false, "Input not Found");
    }


    public ApiResponse addOrUpdate(InputDto dto) {
        Input input = new Input();
        if (dto.getId() != null) {
            input = inputRepo.getById(dto.getId());
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(dto.getWarehouseId());
        if (!optionalWarehouse.isPresent()) {
            return new ApiResponse(false, "Cannot find Warehouse!");
        }
        Optional<Supplier> optionalSupplier = supplierRepo.findById(dto.getSupplierId());
        if (!optionalSupplier.isPresent()) {
            return new ApiResponse(false, "Cannot find Supplier");
        }
        Optional<Currency> optionalCurrency = currencyRepo.findById(dto.getCurrencyId());
        if (!optionalCurrency.isPresent()) {
            return new ApiResponse(false, "Cannot find Currency");
        }

        input.setCode(dto.getCode());
        input.setCurrency(optionalCurrency.get());
        input.setSupplier(optionalSupplier.get());
        input.setWarehouse(optionalWarehouse.get());
        input.setFactureNumber(dto.getFactureNumber());
        inputRepo.save(input);
        return new ApiResponse(true, dto.getId() != null ? "Edited" : "Saved");
    }


    public ApiResponse delete(Long id) {
        Optional<Input> optionalInput = inputRepo.findById(id);
        if (!optionalInput.isPresent()) {
            return new ApiResponse(false, "Error on Deleting!");
        }
        inputRepo.delete(optionalInput.get());
        return new ApiResponse(true, "Deleted!");

    }

}
