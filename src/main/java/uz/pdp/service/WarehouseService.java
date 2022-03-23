package uz.pdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dto.ApiResponse;
import uz.pdp.entity.Warehouse;
import uz.pdp.repository.WarehouseRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarehouseService {


    private final WarehouseRepo warehouseRepo;

    public ApiResponse addOrEdit(Warehouse warehouse) {
        try {
            Warehouse warehouse1 = new Warehouse();
            if (warehouse.getId() != null) {
                warehouse1 = warehouseRepo.getById(warehouse.getId());
            }
            warehouse1.setName(warehouse.getName());
            warehouseRepo.save(warehouse1);
            return new ApiResponse(true, warehouse.getId() != null ? "Edited!" : "Saved");
        } catch (Exception e) {
            return new ApiResponse(false, "Error!");
        }
    }


    public ApiResponse delete(Long id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(id);
        if (!optionalWarehouse.isPresent()) {
            return new ApiResponse(false, "Error on Deleting!");
        }
        warehouseRepo.delete(optionalWarehouse.get());
        return new ApiResponse(true, "Deleted!");
    }
}
