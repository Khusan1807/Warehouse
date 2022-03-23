package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.ApiResponse;
import uz.pdp.entity.Warehouse;
import uz.pdp.repository.WarehouseRepo;
import uz.pdp.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
@RequiredArgsConstructor
public class WarehouseController {


    private final WarehouseService warehouseService;
    private final WarehouseRepo warehouseRepo;


    @GetMapping("/all")
    public List<Warehouse> get() {
        return warehouseRepo.findAll();
    }


    @PostMapping("/addOrEdit")
    public ApiResponse addOrEdit(@RequestBody Warehouse warehouse) {
        return warehouseService.addOrEdit(warehouse);
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        return warehouseService.delete(id);
    }

}
